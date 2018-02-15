package yfcdb.view.coordinatorView;

import yfcdb.events.Event;
import yfcdb.events.EventList;
import yfcdb.files.Files;
import yfcdb.member.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by janaldoustorres on 23/05/15.
 */
public class MainWindow extends JFrame {
    private JPanel centerPanel;
    final static JPanel emptyPanel = new JPanel();
    private final SidePanel sidePanel = new SidePanel();
    private final static Dimension preferredSize = new Dimension(1000, 700);

    private class SidePanel extends JPanel implements Observer {
        private JTextField jtfSearchBar;
        private DefaultListModel personListModel, eventListModel;
        private JList jList;
        private SidePanel() {
            setLayout(new BorderLayout());

            jtfSearchBar = new JTextField(15);
            jtfSearchBar.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    String filterStr = jtfSearchBar.getText().toLowerCase();

                    if (!filterStr.isEmpty()) {
                        if (jList.getModel() == personListModel) {
                            populatePersonList(filterStr);
                        } else if (jList.getModel() == eventListModel) {
                            populateEventList(filterStr);
                        }
                    } else {
                        populateLists();
                    }
                }
            });

            personListModel = new DefaultListModel();
            eventListModel = new DefaultListModel();

            populateLists();

            jList = new JList(personListModel);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int doubleClick = 2;
                    JList list = (JList)e.getSource();
                    if (list.getModel() == personListModel) {
                        if (e.getClickCount() == doubleClick) {// Double-click detected
                            int index = list.locationToIndex(e.getPoint());
                            Person personToShow = (Person) personListModel.getElementAt(index);

                            if (personListModel.getElementAt(index) instanceof Member) {
                                changeCenterPanelToMember((Member) personToShow);
                            } else if (personListModel.getElementAt(index) instanceof Coordinator) {
                                changeCenterPanelToCoordinator((Coordinator) personToShow);
                            }
                        }
                    } else if (list.getModel() == eventListModel) {
                        if (e.getClickCount() == doubleClick) {// Double-click detected
                            int index = list.locationToIndex(e.getPoint());

                            Event eventToShow = (Event) eventListModel.getElementAt(index);
                            changeCenterPanelToEvent(eventToShow);
                        }
                    }
                }
            });


            add(jtfSearchBar, BorderLayout.NORTH);
            add(jList, BorderLayout.CENTER);

            JPanel jpBottom = new JPanel(new GridLayout(1,2));
            JButton jbMember = new JButton("Members");
            jbMember.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO add observer here when a member is added/deleted
                    populatePersonList();
                    jList.setModel(personListModel);
                }
            });
            jpBottom.add(jbMember);
            JButton jbEvent = new JButton("Events");
            jbEvent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    populateEventList();
                    jList.setModel(eventListModel);
                }
            });
            jpBottom.add(jbEvent);

            add(jpBottom, BorderLayout.SOUTH);

            PersonList.getInstance().addObserver(this);
            EventList.getInstance().addObserver(this);
        }

        private void populatePersonList() {
            personListModel.clear();
            PersonList personList = PersonList.getInstance();
            for (Person person: personList.getPersonArrayList()) {
                personListModel.addElement(person);
            }
        }

        private void populatePersonList(String filterStr) {
            personListModel.clear();
            PersonList personList = PersonList.getInstance();
            for (Person person: personList.getPersonArrayList()) {
                String personStr = person.toString().toLowerCase();
                if (personStr.contains(filterStr)) {
                    personListModel.addElement(person);
                }
            }
        }

        private void populateEventList() {
            eventListModel.clear();
            EventList eventList = EventList.getInstance();
            for (Event event: eventList.getEventArrayList()) {
                eventListModel.addElement(event);
            }
        }

        private void populateEventList(String filterStr) {
            eventListModel.clear();
            EventList eventList = EventList.getInstance();
            for (Event event: eventList.getEventArrayList()) {
                String eventStr = event.toString().toLowerCase();
                if (eventStr.contains(filterStr)) {
                    eventListModel.addElement(event);
                }
            }
        }

        private void populateLists() {
            populateEventList();
            populatePersonList();
        }

        @Override
        public void update(Observable o, Object arg) {
            populateLists();
        }
    }

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMenuBar(setupMenuBar());
        setPreferredSize(preferredSize);
        setTitle("YFC");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveFiles(); System.exit(EXIT_ON_CLOSE);
            }
        });

        centerPanel = emptyPanel;

        uploadFiles();

        add(sidePanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        pack();
    }

    private MenuBar setupMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu mFile = new Menu("File");
        MenuItem miAbout = new MenuItem("About");
        mFile.add(miAbout);
        MenuItem miUploadJSON = new MenuItem("Upload JSON");
        miUploadJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Files.chooseUpload();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mFile.add(miUploadJSON);
        MenuItem miDownloadJSON = new MenuItem("Download JSON");
        miDownloadJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Files.downloadJSON();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mFile.add(miDownloadJSON);
        MenuItem miMakeReport = new MenuItem("Make report");
        miMakeReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWizardDialog();
            }
        });
        mFile.add(miMakeReport);
        MenuItem miUpload = new MenuItem("Upload");
        miUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFiles();
            }
        });
        mFile.add(miUpload);
        MenuItem miSave = new MenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFiles();
            }
        });
        mFile.add(miSave);

        Menu mEdit = new Menu("Edit");
        Menu mView = new Menu("View");
        MenuItem miViewMembers = new MenuItem("View member table");
        miViewMembers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToMemberTable();
            }
        });
        mView.add(miViewMembers);
        MenuItem miViewEvents = new MenuItem("View events table");
        miViewEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToEventTable();
            }
        });
        mView.add(miViewEvents);
        MenuItem miViewPastoralFormation = new MenuItem("View pastoral formation attendance table");
        miViewPastoralFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToPastoralFormation();
            }
        });
        mView.add(miViewPastoralFormation);
        MenuItem miClear = new MenuItem("Clear");
        miClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToEmpty();
            }
        });
        mView.add(miClear);

        Menu mEvents = new Menu("Events");
        MenuItem miAddEvent = new MenuItem("Add event");
        miAddEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToNewEvent();
            }
        });
        mEvents.add(miAddEvent);


        Menu mMember = new Menu("Member");
        MenuItem miAddMember = new MenuItem("Add member");
        miAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToNewMember();
            }
        });
        mMember.add(miAddMember);
        MenuItem miAddCoordinator = new MenuItem("Add coordinator");
        miAddCoordinator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCenterPanelToNewCoordinator();
            }
        });
        mMember.add(miAddCoordinator);

        menuBar.add(mFile);
        menuBar.add(mEdit);
        menuBar.add(mView);
        menuBar.add(mEvents);
        menuBar.add(mMember);

        return menuBar;
    }

    private void changeCenterPanelToNewCoordinator() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("<html><h1>New Coordinator</h1></html>"), BorderLayout.NORTH);
        panel.add(new CoordinatorInfoPanel(this, new Coordinator()), BorderLayout.CENTER);
        changeCenterPanel(panel);
    }

    public void changeCenterPanelToCoordinator(Coordinator coordinator) {
        changeCenterPanel(new CoordinatorInfoPanel(this, coordinator));
    }

    private void changeCenterPanelToPastoralFormation() {
        changeCenterPanel(new PastoralFormationTablePanel());
    }

    private void changeCenterPanelToNewEvent() {
        //TODO make MemberInfoPanel a singleton, to make it more efficient
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("<html><h1>New Event</h1></html>"), BorderLayout.NORTH);
        panel.add(new EventsInfoPanel(this), BorderLayout.CENTER);
        changeCenterPanel(panel);
    }

    private void changeCenterPanelToNewMember() {
        //TODO make MemberInfoPanel a singleton, to make it more efficient
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("<html><h1>New Member</h1></html>"), BorderLayout.NORTH);
        panel.add(new JScrollPane(new MemberInfoPanel(this, new Member())), BorderLayout.CENTER);
        changeCenterPanel(panel);
    }

    public void changeCenterPanelToMember(Member member) {
        //TODO make MemberInfoPanel a singleton, to make it more efficient
        changeCenterPanel(new MemberTabbedPanel(this, member));
    }

    public void changeCenterPanelToEvent(Event event) {
        //TODO make MemberInfoPanel a singleton, to make it more efficient
        changeCenterPanel(new EventsInfoPanel(this, event));
    }

    private void changeCenterPanelToMemberTable() {
        changeCenterPanel(new MembersTablePanel());
    }

    private void changeCenterPanelToEventTable() {
        changeCenterPanel(new EventsTablePanel());
    }

    public void changeCenterPanelToReportTable(Date start, Date end) {
        changeCenterPanel(new ReportPanel(start, end));
    }

    public void changeCenterPanelToEmpty() {
        changeCenterPanel(emptyPanel);
    }

    private void changeCenterPanel(JPanel panel) {
        remove(centerPanel);
        centerPanel = panel;
        add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showWizardDialog() {
        new ReportWizardDialog(this).setVisible(true);
    }

    private void saveFiles() {
        try {
            Files.saveToFile();
            JOptionPane.showMessageDialog(this, "File saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadFiles() {
        try {
            Files.uploadFromFile();
            sidePanel.populateLists();
            JOptionPane.showMessageDialog(this, "Upload successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}

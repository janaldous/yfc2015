package yfcdb.events;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import yfcdb.member.Member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Embeddable
public class Attendee {
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("member_id")
    private Member member;
    
    private Role role;
    
    
    public Attendee() {}

    public Attendee(Member member, Role role) {
        this.member = member;
        this.role = role;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Member getMember() {
    	return member;
    }

    public String toString() {
        return member.getId() + ":" + member.toString() + " - " + role.toString();
    }
}

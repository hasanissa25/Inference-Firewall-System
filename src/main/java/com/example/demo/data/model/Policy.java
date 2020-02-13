package com.example.demo.data.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Policy{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_generator")
    @SequenceGenerator(name="policy_generator", sequenceName = "policy_seq")
    private Long id;
    @ElementCollection
    private List<String> inputColumns;
    @ElementCollection 
    private List<String> blockedColumns;
    private String relationship;

    public List<String> getInputColumns()
    {
        return inputColumns;
    }

    public List<String> getBlockedColumns()
    {
        return blockedColumns;
    }

    public String getRelationship()
    {
        return relationship;
    }
    
    public Queue<String> getRelationshipOperators(){
        //relationship = "patient_info.date_of_leave - patient_info.date_of_entry != patient_medical_info.length_of_stay";
        relationship = relationship.trim();
        String[] tokens = relationship.split("(\\s+)");
        Queue<String> operators = new LinkedList<String>();
        for(String token:tokens){ 
            if(!token.matches("[a-zA-Z_\\.]+")){
                operators.add(token);
            }
        }

        return operators;
    }

    public ArrayList<String> getRelationshipOperands(){
        //relationship = "patient_info.date_of_leave - patient_info.date_of_entry != patient_medical_info.length_of_stay";
        relationship = relationship.trim();
        String[] tokens = relationship.split("(\\s+)");
        ArrayList<String> operands = new ArrayList<String>();
        for(String token:tokens){ 
            if(token.matches("[a-zA-Z_\\.]+")){
                operands.add(token);
            }
        }

        return operands;
    }

}

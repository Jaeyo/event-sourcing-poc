package org.jaeyo.eventsourcingpoc.domain.elect.model

import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "election_gender")
data class ElectionGender(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @ManyToOne(targetEntity = Candidate::class) @JoinColumn(name = "candidate_id") @NotNull
    val candidate: Candidate,
    @Column(name = "gender") @NotNull
    val gender: Gender,
    @Column @NotNull
    val votes: Long
)

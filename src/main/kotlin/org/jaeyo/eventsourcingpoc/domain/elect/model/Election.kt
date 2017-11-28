package org.jaeyo.eventsourcingpoc.domain.elect.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "election")
data class Election(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @ManyToOne(targetEntity = Candidate::class) @JoinColumn(name = "candidate_id") @NotNull
    val candidate: Candidate,
    @Column @NotNull
    val votes: Long
)

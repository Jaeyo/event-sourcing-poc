package org.jaeyo.eventsourcingpoc.domain.elect.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "election_region")
data class ElectionRegion(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @ManyToOne(targetEntity = Candidate::class) @JoinColumn(name = "candidate_id") @NotNull
    val candidate: Candidate,
    @ManyToOne(targetEntity = Region::class) @JoinColumn(name = "region_id") @NotNull
    val region: Region,
    @Column @NotNull
    val votes: Long
)
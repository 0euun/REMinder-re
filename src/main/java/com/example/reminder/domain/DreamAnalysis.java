package com.example.reminder.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dream_analysis")
public class DreamAnalysis extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dream_analysis_id")
    private Long dreamAnalysisId;

    @Lob
    @Column(name = "analysis_text")
    private String analysisText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dream")
    private Dream dream;
}

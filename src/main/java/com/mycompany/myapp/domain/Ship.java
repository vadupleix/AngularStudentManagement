package com.mycompany.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ship")
@Data
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, columnDefinition = "bigint")
    private long id;

    @Column(name = "ship_name", nullable = false, length = 100, columnDefinition = "nvarchar(100)")
    private String shipName;

    @Column(name = "country", nullable = false, length = 30, columnDefinition = "nvarchar(30)")
    private String country;

    @Column(name = "ship_type", nullable = false, length = 30, columnDefinition = "nvarchar(30)")
    private String type;

    @Column(name = "freq_very_low", nullable = false, columnDefinition = "bigint")
    private long freqVeryLow;

    @Column(name = "freq_low", nullable = false, columnDefinition = "bigint")
    private long freqLow;

    @Column(name = "freq_med", nullable = false, columnDefinition = "bigint")
    private long freqMed;

    @Column(name = "freq_high", nullable = false, columnDefinition = "bigint")
    private long freqHigh;

    @Column(name = "freq_very_high", nullable = false, columnDefinition = "bigint")
    private long freqVeryHigh;

    @Column(name = "freq_active", nullable = true, columnDefinition = "bigint")
    private long freqActive;

    @Column(name = "tpk", nullable = false, columnDefinition = "bigint")
    private long tpk;

    @Column(name = "num_blades", nullable = false, columnDefinition = "bigint")
    private long numBlades;
}

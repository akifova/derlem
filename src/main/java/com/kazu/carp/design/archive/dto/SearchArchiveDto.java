package com.kazu.carp.design.archive.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author akifova
 * 07.08.2021
 */
@Getter @Setter @NoArgsConstructor
public class SearchArchiveDto {
    private String designName;  //Like Textbox
    private short starRating;  //ge gt le lt 5 Yıldız
    private Integer difficulty;  //between 0..100
    private short versionNumber; //gt Integer
    private String tag; // like textbox
    private ArchiveStatus archiveStatus; //dropdown
    private String collectionId; //Lookup, Multi Choice
    private String qualityId; //Lookup, Multi Choice
    private String paletteId; //Lookup, Multi Choice
    //private String yarnId; //Lookup, Multi Choice
    //private String colorId; //Lookup, Multi Choice
    private String bordureId; //Lookup, Multi Choice
    private String floorId; //Lookup, Multi Choice
    private String sizeId; //Lookup, Multi Choice
    private String designId; //Lookup, Multi Choice
    private String shapeId; //Lookup, Multi Choice
    private String edgeId; //Lookup, Multi Choice
    private String overlockYarnId; //Lookup, Multi Choice
    private String sideEdgeId; //Lookup, Multi Choice
    private WeaveType weaveType; //DropDown
    private boolean makingRare; //Boolean
    private String customerId; //Lookup, Multi Choice
    private String designPlanId; //Lookup, Multi Choice
    private String designerId; //Lookup, Multi Choice
}

package com.kazu.carp.common.address.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by sarparda on 16.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class MailAndPhoneListDto {
    private List<MailDto> mails;
    private List<PhoneDto> phones;

    public MailAndPhoneListDto(List<MailDto> mails, List<PhoneDto> phones) {
        this.phones = phones;
        this.mails = mails;
    }
}

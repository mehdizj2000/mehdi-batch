package au.com.jaycar.batch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity
@Immutable
@Table(name = "address_info")
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String address_detail_pid;
    private String street_locality_pid;
    private String locality_pid;
    private String building_name;
    private String lot_number_prefix;
    private String lot_number;
    private String lot_number_suffix;
    private String flat_type;
    private String flat_number_prefix;
    private String flat_number;
    private String flat_number_suffix;
    private String level_type;
    private String level_number_prefix;
    private String level_number;
    private String level_number_suffix;
    private String number_first_prefix;
    private String number_first;
    private String number_first_suffix;
    private String number_last_prefix;
    private String number_last;
    private String number_last_suffix;
    private String street_name;
    private String street_class_code;
    private String street_class_type;
    private String street_type_code;
    private String street_suffix_code;
    private String street_suffix_type;
    private String locality_name;
    private String state_abbreviation;
    private String postcode;
    private String latitude;
    private String longitude;
    private String geocode_type;
    private String confidence;
    private String alias_principal;
    private String primary_secondary;
    private String legal_parcel_id;

}

/*
 *  Copyright (c) 2019. CodeGen Ltd. - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Created by Dulaj Pathirana on 4/5/19 1:37 AM.
 */

package it.codegen.crawler.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
//@JsonPropertyOrder({ "hotelId", "hotelName", "city", "country", "offset", "duration" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrawlingCriteria implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int criteriaId;
	private String hotelId;
	private String hotelName;
	private String city;
	private String country;
	private String offset;
	private String duration;

	public String getHotelId()
	{
		return hotelId;
	}
	@JsonProperty("Unique_Code")
	public void setHotelId( String hotelId )
	{
		this.hotelId = hotelId;
	}

	public String getHotelName()
	{
		return hotelName;
	}
	@JsonProperty("Hotel_name")
	public void setHotelName( String hotelName )
	{
		this.hotelName = hotelName;
	}

	public String getCountry()
	{
		return country;
	}
	@JsonProperty("Country")
	public void setCountry( String country )
	{
		this.country = country;
	}


	public String getDuration()
	{
		return duration;
	}
	@JsonProperty("ALOS")
	public void setDuration( String duration )
	{
		this.duration = duration;
	}

	public String getCity()
	{
		return city;
	}
	@JsonProperty("City_name")
	public void setCity( String city )
	{
		this.city = city;
	}


	public String getOffset()
	{
		return offset;
	}
	@JsonProperty("OFFSET (Days)")
	public void setOffset( String offset )
	{
		this.offset = offset;
	}
}

package com.lawencon.linovhrcommunity.dto.usermember;

import javax.validation.constraints.NotNull;

public class UpdateUserMemberAcceptDtoReq {
	@NotNull(message = "Acceptance is Empty")
	private Boolean isAccept;

	@NotNull(message = "UserMember is Empty")
	private String idUserMember;

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}

	public String getIdUserMember() {
		return idUserMember;
	}

	public void setIdUserMember(String idUserMember) {
		this.idUserMember = idUserMember;
	}

}

package com.asherkhan.helpers.postcodeSplitter;

public class PostcodeSplitter {
	
	public static PostcodeBean splitPostcode(String postcode) {
		PostcodeBean splitCode = new PostcodeBean();
		StringBuilder outwardCode = new StringBuilder("");
		StringBuilder inwardCode = new StringBuilder("");
		boolean isInward = false;

		if (postcode != null && postcode.length() > 0) {
			for (int i = 0; i < postcode.trim().length(); i++) {
				char ch = postcode.toUpperCase().charAt(i);
				char nextCh = ' ';
				switch (i) {
				case 0:
					if (ch >= 'A' && ch <= 'Z') {
						outwardCode.append(ch);
					}
					break;
				case 1:
					if (ch >= 'A' && ch <= 'Z') {
						outwardCode.append(ch);
					} else {
						if (ch >= '0' && ch <= '9') {
							outwardCode.append(ch);
							if (postcode.trim().length() - 1 > i) {
								nextCh = postcode.toUpperCase().charAt(i + 1);
								if (nextCh >= '0' && nextCh <= '9') {
									if (postcode.trim().length() - 2 > i) {
										nextCh = postcode.toUpperCase().charAt(
												i + 2);
										if (nextCh >= 'A' && nextCh <= 'Z') {
											isInward = true;
										}
									}
								}
							}
						}
					}
					break;
				case 2:
					if (ch == ' ') {
						isInward = true;
					} else {
						if (isInward) {
							inwardCode.append(ch);
						} else {
							if (ch >= 'A' && ch <= 'Z') {
								outwardCode.append(ch);
							} else {
								if (ch >= '0' && ch <= '9') {
									outwardCode.append(ch);
									if (postcode.trim().length() - 1 > i) {
										nextCh = postcode.toUpperCase().charAt(
												i + 1);
										if (nextCh >= '0' && nextCh <= '9') {
											if (postcode.trim().length() - 2 > i) {
												nextCh = postcode.toUpperCase()
														.charAt(i + 2);
												if (nextCh >= 'A'
														&& nextCh <= 'Z') {
													isInward = true;
												}
											}
										}
									}
								}
							}
						}
					}
					break;
				case 3:
					if (isInward) {
						if (ch >= '0' && ch <= '9') {
							inwardCode.append(ch);
						} else {
							if (ch >= 'A' && ch <= 'Z') {
								inwardCode.append(ch);
							}
						}
					} else {
						if (ch == ' ') {
							isInward = true;
						} else {
							if (ch >= 'A' && ch <= 'Z') {
								outwardCode.append(ch);
							} else {
								if (ch >= '0' && ch <= '9') {
									if (postcode.trim().length() - 1 > i) {
										nextCh = postcode.toUpperCase().charAt(
												i + 1);
										if (nextCh >= 'A' && nextCh <= 'Z') {
											isInward = true;
											inwardCode.append(ch);
										} else {
											outwardCode.append(ch);
										}
									} else {
										outwardCode.append(ch);
									}
								}
							}
						}
					}
					break;
				case 4:
					if (ch == ' ') {
						isInward = true;
					} else {
						if (ch >= 'A' && ch <= 'Z') {
							inwardCode.append(ch);
						} else {
							if (ch >= '0' && ch <= '9') {
								inwardCode.append(ch);
							}
						}
					}
					break;
				case 5:
					if (ch >= 'A' && ch <= 'Z') {
						inwardCode.append(ch);
					} else {
						if (ch >= '0' && ch <= '9') {
							inwardCode.append(ch);
						}
					}
					break;
				case 6:
					if (ch >= 'A' && ch <= 'Z') {
						inwardCode.append(ch);
					}
					break;
				case 7:
					if (ch >= 'A' && ch <= 'Z') {
						inwardCode.append(ch);
					}
					break;
				default:
					break;
				}
			}
		}
		splitCode.setOutwardCode(outwardCode.toString());
		splitCode.setInwardCode(inwardCode.toString());

		return splitCode;
	}
}
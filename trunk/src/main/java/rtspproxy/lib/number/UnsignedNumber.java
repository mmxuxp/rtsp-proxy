/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   Copyright (C) 2005 - Matteo Merli - matteo.merli@gmail.com            *
 *                                                                         *
 ***************************************************************************/

/*
 * $Id: UnsignedNumber.java 316 2005-12-04 14:48:53Z merlimat $
 * 
 * $URL: http://svn.berlios.de/svnroot/repos/rtspproxy/tags/3.0-ALPHA2/src/main/java/rtspproxy/lib/number/UnsignedNumber.java $
 * 
 */
package rtspproxy.lib.number;

/**
 * @author Matteo Merli
 */
public abstract class UnsignedNumber extends Number {
	/**
	 * Get a byte array representation of the number. The order will be MSB
	 * first (Big Endian).
	 * 
	 * @return the serialized number
	 */
	public abstract byte[] getBytes();

	/**
	 * Perform a bit right shift of the value.
	 * 
	 * @param nBits
	 *            the number of positions to shift
	 */
	public abstract void shiftRight(int nBits);

	/**
	 * Perform a bit left shift of the value.
	 * 
	 * @param nBits
	 *            the number of positions to shift
	 */
	public abstract void shiftLeft(int nBits);

	public abstract String toString();

	public abstract int compareTo(UnsignedNumber other);

	public abstract boolean equals(Object other);
	
	public abstract int hashCode();

	public String toHexString() {
		return toHexString(false);
	}

	public String toHexString(boolean pad) {
		StringBuilder sb = new StringBuilder();
		boolean started = false;
		for (byte b : getBytes())
			if (!started && b == 0) {
				if (pad)
					sb.append("00");
			} else {
				sb.append(hexLetters[(byte) ((b >> 4) & 0x0F)]).append(
						hexLetters[b & 0x0F]);
				started = true;
			}
		if (sb.length() == 0)
			return "0";
		return sb.toString();
	}

	protected static final char[] hexLetters = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
}

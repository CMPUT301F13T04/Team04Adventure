/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.team04adventure.Model;

public class compressedStory {

	String title;
	int numfrags;
	String id;
	String author;
	

	public compressedStory(Story aStory){
		
		this.title = aStory.getTitle();
		this.author = aStory.getAuthor();
		this.id = aStory.getId();
		this.numfrags = aStory.getFrags().size();
	}

	public Story toStory(){
		Story tempStory = new Story();
		tempStory.setTitle(this.title);
		tempStory.setAuthor(this.author);
		tempStory.setId(this.id);
		for (int b = 0; b<this.numfrags;b++){
			Frag tempFrag = new Frag();
			tempStory.getFrags().add(tempFrag);
		}
		return tempStory;
		
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getNumfrags() {
		return numfrags;
	}


	public void setNumfrags(int numfrags) {
		this.numfrags = numfrags;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
}

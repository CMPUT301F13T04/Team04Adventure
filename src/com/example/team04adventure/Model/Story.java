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


import java.util.ArrayList;

/**
 * Story is meant to create and hold the ID, title, author and all associated fragments of a story.
 * 
 * @author Team04Adventure
 */
public class Story {
	
	private String title;
	private ArrayList<Frag> frags;
	private String id;
	private String Author;
	private String synopsis;
	private int version;
	
	public Story(){
		this.frags = new ArrayList<Frag>();
		this.title = "";
		this.Author = "";
		this.synopsis = "";
		this.setVersion(0);
	}
	
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String syn){
		this.synopsis = syn;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setAuthor(String author){
		this.Author = author;
	}
	
	/**
	 * Adds the given fragment as one of the associated fragments of the story.
	 * @param frag the fragment to add.
	 */
	public void addFragment(Frag frag){
		this.frags.add(frag);
	}
	
	/**
	 * Adds the given fragment as one of the associated fragments of the story in the set position.
	 * @param frag the fragment to add.
	 * @param position the position in the ArrayList of stories to add the story to.
	 */
	public void addFragment(Frag frag, int position) {
		this.frags.add(position, frag);
	}

	/**
	 * Deletes the fragment.
	 * @param id id of the fragment to delete.
	 */
	public void deleteFrag(String id) {
		for (int i = 0; i < frags.size(); i++) {
			if (frags.get(i).getId().equals(id)) {
				frags.remove(i);
			}
		}
	}
	
	public Frag getFrag(String fid) {
		for (int i = 0; i < frags.size(); i++) {
			if (frags.get(i).getId().equals(fid)) {
				return frags.get(i);
			}
		}
		return null;
	}

	public String getTitle(){
		return this.title;
	}
	
	public String getAuthor(){	
		return this.Author;
	}
	
	public ArrayList<Frag> getFrags(){
		return this.frags;
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Increments the version of the story.
	 */
	public void incVersion(){
		(this.version)++;
	}
}



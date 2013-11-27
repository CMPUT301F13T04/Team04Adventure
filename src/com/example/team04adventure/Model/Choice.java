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

/**
 * Class that models a choice associated with a fragment item. It contains
 * text(body) that differentiates it from other choices, an ID for storage and
 * linking purposes, and a childID that links to the fragment it represents.
 * 
 * @author Team04Adventure
 */
public class Choice {
	
	String body;
	long id;
	String childID;
	
	public Choice(){
		this.childID = "";
		this.body = "";
	}
	
	public String getBody(){
		return this.body;	
	}

	public void setBody(String body){	
		this.body = body;	
	}

	public void setChild(String id){		
		this.childID = id;
	}

	public String getChild(){	
		return this.childID;
	}
	
	public long getID(){	
		return this.id;
	}
	
	public void setID(long id){	
		this.id = id;
	}

}

package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.repo.SpringUSAState;
import com.nt.state.UsaState;


@Service("stservice")
public class USAStateserviceImpl implements USAStatesevice{
	
	@Autowired
	SpringUSAState usaState;
	
	public  String RegisterState() {
		UsaState state=new UsaState();
		state.setStateCode("us");
		state.setSTATENAME("koris");
		state=usaState.save(state);
		return state!=null?"added succsesfully":"failed";
	}
	
	/*while(itr.hasNext()) {
		   key=itr.next();
		   value=obj.get(key).getAsString();
		   
		   state.setStateCode(key);
		   state.setSTATENAME(value);
		   System.out.println(state);
		
		   
	}*/
	
	
}

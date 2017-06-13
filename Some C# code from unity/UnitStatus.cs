using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UnitStatus : MonoBehaviour {

    private bool selected;
    private bool alive;

	void Start ()
    {
        selected = alive = false;
	}
	
	public bool Selected
    {
        get
        {
            return selected;
        }

        set
        {
            selected = value;
        }
    }

    public bool Alive
    {
        get
        {
            return alive;
        }

        set
        {
            alive = value;
        }
    }
}
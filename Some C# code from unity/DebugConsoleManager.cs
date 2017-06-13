using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DebugConsoleManager : MonoBehaviour {

    DebugConsoleInfo debugConsoleInfo;

	// Use this for initialization
	void Start ()
    {
        debugConsoleInfo = Toolbox.GetScript<DebugConsoleInfo>();
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.GetKeyUp(KeyCode.F12))
        {
            if (debugConsoleInfo.gameObject.activeInHierarchy)
            {
                debugConsoleInfo.gameObject.SetActive(false);
            }
            else
            {
                debugConsoleInfo.gameObject.SetActive(true);
            }
        }
	}
}

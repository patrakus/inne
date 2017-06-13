using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DebugConsoleInfo : MonoBehaviour {

    public Text selectedUnitsText;

    private UnitManager unitManager;
    
    void Awake()
    {
        Toolbox.AddScript(this);
    }

	// Use this for initialization
	void Start ()
    {
        unitManager = Toolbox.GetScript<UnitManager>();

        if (!unitManager)
            Debug.LogError("No reference for unitManager");

        if (!selectedUnitsText)
            Debug.LogError("No reference for selectedUnitsReference");

        if (gameObject.activeInHierarchy)
            gameObject.SetActive(false);
	}
	
	// Update is called once per frame
	void Update ()
    {
        if (gameObject.activeInHierarchy)
        {
            selectedUnitsText.text = unitManager.GetSelectedUnitsCount().ToString();
        }
    }
}
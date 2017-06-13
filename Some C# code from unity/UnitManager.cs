using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UnitManager : MonoBehaviour {

    List<UnitController> unitsControllerList = new List<UnitController>();
    List<UnitController> selectedUnits = new List<UnitController>();
    //Ray mouseRay;

    UnitSelection _unitSelection;

    public Collider groundCollider;

    public List<UnitController> unitsList
    {
        get
        {
            return unitsControllerList;
        }
    }
    
    void Awake()
    {
        Toolbox.AddScript(this);
    }    

    void Start()
    {
        _unitSelection = Toolbox.GetScript<UnitSelection>();
    }
	
	// Update is called once per frame
	void Update ()
    {
        if (Input.GetMouseButton(0))
        {
            ClearSelectedUnits();
            AddSelectedUnit();
        }
    }

    public void AddUnit(UnitController unitController)
    {
        unitsControllerList.Add(unitController);
    }

    private void AddSelectedUnit()
    {
        foreach (UnitController unitController in unitsControllerList)
        {
            if (_unitSelection.IsWithinSelectionBounds(unitController.gameObject))
            {
                selectedUnits.Add(unitController);
                Debug.Log("Selected: " + unitController.name);
                unitController.unitStatus.Selected = true;
            }
        }
    }

    public void AddSelectedUnit(UnitController unitController)
    {
        unitController.unitStatus.Selected = true;
        selectedUnits.Add(unitController);
    }

    public void ClearSelectedUnits()
    {
        foreach (UnitController unitController in selectedUnits)
        {
            unitController.unitStatus.Selected = false;
        }

        selectedUnits.Clear();
    }

    public int GetSelectedUnitsCount()
    {
        return selectedUnits.Count;
    }
}

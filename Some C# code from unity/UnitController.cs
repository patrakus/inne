using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UnitController : MonoBehaviour {

    public bool engaged = false;
    public UnitStatus unitStatus;

    Animator unitAnimator;
    UnitManager unitManager;

    int runHash = Animator.StringToHash("run");
    int chargeHash = Animator.StringToHash("charge");
    int attackHash = Animator.StringToHash("attack");

    UnitSelection unitSelection = Toolbox.GetScript<UnitSelection>();

    private Vector3 target;

    public Vector3 Target
    {
        get { return target; }

        set
        {
            target = value;

            StopCoroutine("Movement");
            StartCoroutine("Movement", target);
        }
    }

	void Start ()
    {
        unitManager = Toolbox.GetScript<UnitManager>();

        if (!unitManager)
            Debug.LogError("No reference for unitManager");

        unitManager.AddUnit(this);

        unitAnimator = GetComponent<Animator>();
	}
	
	// Update is called once per frame
	void Update ()
    {
        if (engaged)
        {
            unitAnimator.SetTrigger(attackHash);
            engaged = false;
        }
            
	}

    public void SetRun(bool state)
    {
        unitAnimator.SetBool(runHash, state);
    }

    public void SetCharge(bool state)
    {
        unitAnimator.SetBool(runHash, state);
    }

    public void OnMouseUp()
    {
        Debug.Log("Selected " + name);
        unitManager.AddSelectedUnit(this);
    }

    IEnumerator Movement(Vector3 target)
    {
        //Quaternion newRotation = Quaternion.FromToRotation(transform.position, target);

        //transform.rotation = newRotation;

        //unitAnimator.SetBool(runHash, true);
        while (Vector3.Distance(transform.position, target) > 0.00)
        {
            transform.position = Vector3.MoveTowards(transform.position, target, 5 * Time.deltaTime);
            //transform.rotation = Quaternion.RotateTowards(transform.rotation, newRotation,5 * Time.deltaTime);
            //if(target.x > rot.x && target.y > rot.y)
            //{
            //    rot += Time.deltaTime * target;
            //    transform.LookAt(rot);
            //}

            yield return null;
        }
        unitAnimator.SetBool(runHash, false);

        print("Movement have just finished");
    }
}
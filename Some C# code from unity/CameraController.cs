using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

    public float speed = 3f;

	// Use this for initialization
	void Start ()
    {
        	
	}
	
	// Update is called once per frame
	void Update ()
    {
        float vertical = Input.GetAxis("Vertical");
        float horizontal = Input.GetAxis("Horizontal");

        Vector3 newPos = new Vector3(speed * -horizontal * Time.deltaTime, 0f, speed * -vertical * Time.deltaTime);
        newPos += transform.localPosition;

        transform.localPosition = newPos;
    }
}

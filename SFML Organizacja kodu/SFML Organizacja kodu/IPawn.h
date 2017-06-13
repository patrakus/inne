#pragma once
#include "IPawnController.h"
#include "IActor.h"

class IPawn : public IActor
{
public:
	explicit IPawn(IPawnController *controller); // TODO: sprawdziæ funkcje s³owa explicit w deklaracji konstruktora;
	virtual ~IPawn();

	void ResetController(IPawnController* controller);

	virtual void Update(const float& deltaTime) override; // TODO: sprawdziæ funkcje s³owa override w deklaracji metody;

protected:
	IPawnController* m_controller;
};


#include "IPawn.h"



IPawn::IPawn(IPawnController *controller) : m_controller(nullptr)
{
	if (controller && controller->Possess(this))
	{
		m_controller = controller;
	}
}


IPawn::~IPawn()
{
	if (m_controller)
		delete m_controller;
}

void IPawn::ResetController(IPawnController * controller)
{
	if (controller != m_controller)
	{
		if (m_controller)
			delete m_controller;

		if (controller && controller->Possess(this))
			m_controller = controller;
		else
			m_controller = nullptr;
	}
}

void IPawn::Update(const float & deltaTime)
{
	if (m_controller)
		m_controller->Update(deltaTime);
}

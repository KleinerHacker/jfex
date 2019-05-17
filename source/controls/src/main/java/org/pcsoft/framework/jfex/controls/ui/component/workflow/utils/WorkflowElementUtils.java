package org.pcsoft.framework.jfex.controls.ui.component.workflow.utils;

import org.pcsoft.framework.jfex.controls.ui.component.workflow.element.WorkflowForkElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.element.WorkflowJoinElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.BasicWorkflowElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public final class WorkflowElementUtils {

    public static final class ParentUtils {
        public static WorkflowElement setParentElement(final WorkflowElement workflowElement, final WorkflowElement parentElement, final boolean checkDependencies) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).setParentElement(parentElement, checkDependencies);
            } else if (workflowElement instanceof WorkflowForkElement) {
                return ((WorkflowForkElement) workflowElement).setParentElement(parentElement, checkDependencies);
            } else if (workflowElement instanceof WorkflowJoinElement) {
                ((WorkflowJoinElement) workflowElement).getParentElementList().add(parentElement);
                return null;
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static WorkflowElement removeParentElement(final WorkflowElement workflowElement, final WorkflowElement parentElement, final boolean checkDependencies) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).setParentElement(null, checkDependencies);
            } else if (workflowElement instanceof WorkflowForkElement) {
                return ((WorkflowForkElement) workflowElement).setParentElement(null, checkDependencies);
            } else if (workflowElement instanceof WorkflowJoinElement) {
                ((WorkflowJoinElement) workflowElement).getParentElementList().remove(parentElement);
                return null;
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static boolean hasParentElement(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).getParentElement() != null;
            } else if (workflowElement instanceof WorkflowForkElement) {
                return ((WorkflowForkElement) workflowElement).getParentElement() != null;
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return false;
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static WorkflowElement getParentElement(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).getParentElement();
            } else if (workflowElement instanceof WorkflowForkElement) {
                return ((WorkflowForkElement) workflowElement).getParentElement();
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return null;
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static List<WorkflowElement> getParentElementList(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return Collections.singletonList(((BasicWorkflowElement) workflowElement).getParentElement()).stream()
                        .filter(item -> item != null)
                        .collect(Collectors.toList());
            } else if (workflowElement instanceof WorkflowForkElement) {
                return Collections.singletonList(((WorkflowForkElement) workflowElement).getParentElement()).stream()
                        .filter(item -> item != null)
                        .collect(Collectors.toList());
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return new ArrayList<>(((WorkflowJoinElement) workflowElement).getParentElementList());
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        private ParentUtils() {
        }
    }

    public static final class ChildUtils {
        public static WorkflowElement setChildElement(final WorkflowElement workflowElement, final WorkflowElement childElement, final boolean checkDependencies) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).setChildElement(childElement, checkDependencies);
            } else if (workflowElement instanceof WorkflowForkElement) {
                ((WorkflowForkElement) workflowElement).getChildElementList().add(childElement);
                return null;
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return ((WorkflowJoinElement) workflowElement).setChildElement(childElement, checkDependencies);
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static WorkflowElement removeChildElement(final WorkflowElement workflowElement, final WorkflowElement childElement, final boolean checkDependencies) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).setChildElement(null, checkDependencies);
            } else if (workflowElement instanceof WorkflowForkElement) {
                ((WorkflowForkElement) workflowElement).getChildElementList().remove(childElement);
                return null;
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return ((WorkflowJoinElement) workflowElement).setChildElement(null, checkDependencies);
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static boolean hasChildElement(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).getChildElement() != null;
            } else if (workflowElement instanceof WorkflowForkElement) {
                return false;
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return ((WorkflowJoinElement) workflowElement).getChildElement() != null;
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static WorkflowElement getChildElement(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return ((BasicWorkflowElement) workflowElement).getChildElement();
            } else if (workflowElement instanceof WorkflowForkElement) {
                return null;
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return ((WorkflowJoinElement) workflowElement).getChildElement();
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        public static List<WorkflowElement> getChildElementList(final WorkflowElement workflowElement) {
            if (workflowElement instanceof BasicWorkflowElement) {
                return Collections.singletonList(((BasicWorkflowElement) workflowElement).getChildElement()).stream()
                        .filter(item -> item != null)
                        .collect(Collectors.toList());
            } else if (workflowElement instanceof WorkflowForkElement) {
                return new ArrayList<>(((WorkflowForkElement) workflowElement).getChildElementList());
            } else if (workflowElement instanceof WorkflowJoinElement) {
                return Collections.singletonList(((WorkflowJoinElement) workflowElement).getChildElement()).stream()
                        .filter(item -> item != null)
                        .collect(Collectors.toList());
            } else
                throw new RuntimeException("Unknown element class: " + workflowElement.getClass().getName());
        }

        private ChildUtils() {
        }
    }

    public static WorkflowElement setChildElement(final WorkflowElement rootElement, final WorkflowElement oldChildElement, final WorkflowElement newChildElement, final boolean checkDependencies, final Consumer<WorkflowElement> setChildConsumer) {
        if (checkDependencies) {
            //Fix old child element
            if (oldChildElement != null) {
                //Cleanup old child element (clear parent element)
                ParentUtils.removeParentElement(oldChildElement, rootElement, false);
            }
        }

        //Set new child element
        setChildConsumer.accept(newChildElement);

        if (checkDependencies) {
            //Setup parent for child element
            if (newChildElement != null) {
                //Handling for child element with an other parent element (relink)
                if (ParentUtils.hasParentElement(newChildElement)) {
                    final WorkflowElement parentElement = ParentUtils.getParentElement(newChildElement);
                    //Cleanup
                    ChildUtils.removeChildElement(parentElement, newChildElement, false);
                }

                //Setup this element as parent of new child element
                ParentUtils.setParentElement(newChildElement, rootElement, false);
            }
        }

        return oldChildElement;
    }

    public static WorkflowElement setParentElement(final WorkflowElement rootElement, final WorkflowElement oldParentElement, final WorkflowElement newParentElement, final boolean checkDependencies, final Consumer<WorkflowElement> setParentConsumer) {
        if (checkDependencies) {
            //Fix old parent element
            if (oldParentElement != null) {
                //Cleanup old parent element (clear child element)
                ChildUtils.removeChildElement(oldParentElement, rootElement, false);
            }
        }

        //Set new parent element
        setParentConsumer.accept(newParentElement);

        if (checkDependencies) {
            //Setup child for parent element
            if (newParentElement != null) {
                //Handling for parent element with an other child element (relink)
                if (ChildUtils.hasChildElement(newParentElement)) {
                    final WorkflowElement childElement = ChildUtils.getChildElement(newParentElement);
                    //Cleanup
                    ParentUtils.removeParentElement(childElement, newParentElement, false);
                }

                //Setup this element as child of new parent element
                ChildUtils.setChildElement(newParentElement, rootElement, false);
            }
        }

        return oldParentElement;
    }

    private WorkflowElementUtils() {
    }
}

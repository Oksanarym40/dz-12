package org.example;


import org.testng.annotations.*;

import static org.testng.Assert.*;

    public class PersonTest {

        @DataProvider(name = "people")
        public Object[][] createPeople() {
            return new Object[][] {
                    { new Man("Igor", "Rymaruk", 40) },
                    { new Woman("Oksana", "Shpynta", 55) }
            };
        }

        @Test(dataProvider = "people", priority = 1, description = "Verify if person is retired")
        public void testIsRetired(Person person) {
            boolean expectedRetiredStatus = person instanceof Woman && person.getAge() >= 50;
            assertEquals(person.isRetired(), expectedRetiredStatus);
        }

        @Test(dataProvider = "people", priority = 2, description = "Verify if registered partners")
        public void testSetPartner(Person person) {
            Person partner = person instanceof Man
                    ? new Woman("PartnerFirst", "PartnerLast", 30)
                    : new Man("PartnerFirst", "PartnerLast", 30);

            person.setPartner(partner);

            assertEquals(person, partner.getPartner());
            assertEquals(partner, person.getPartner());
        }

        @Test(dataProvider = "people", priority = 3, description = "Verify if partner's last name change after registerPartnership")
        public void testRegisterPartnership(Person person) {
            String partnerLastName = "PartnerLast";
            Person partner = person instanceof Man
                    ? new Woman("PartnerFirst", partnerLastName, 30)
                    : new Man("PartnerFirst", partnerLastName, 30);

            person.registerPartnership(partner);

            assertEquals(person, partner.getPartner());
            assertEquals(partner, person.getPartner());
            assertEquals(partnerLastName, person.getLastName());
        }

        @Test(dataProvider = "people", priority = 4, description = "Verify if partner's last name return to previous after deregisterPartnership")
        public void testDeregisterPartnership(Person person) {
            Person partner = person instanceof Man
                    ? new Woman("PartnerFirst", "PartnerLast", 30)
                    : new Man("PartnerFirst", "PartnerLast", 30);

            person.setPartner(partner);
            person.deregisterPartnership(true);

            assertNull(person.getPartner());
            assertNull(partner.getPartner());
            assertEquals(person.getLastName(), partner instanceof Man ? "Rymaruk" : "Shpynta");
        }
    }


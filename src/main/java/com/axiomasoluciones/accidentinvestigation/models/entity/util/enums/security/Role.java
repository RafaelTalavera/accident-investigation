package com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.security;

import java.util.Arrays;
import java.util.List;

public enum Role {

        CUSTOMER(Arrays.asList(Permission.READ_ALL_ITEMS)),
        ADMINISTRATOR(Arrays.asList(Permission.SAVE_ONE_ITEMS, Permission.READ_ALL_ITEMS));

        private List<Permission> permissions;

        Role(List<Permission> permissions) {
            this.permissions = permissions;
        }

        public List<Permission> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<Permission> permissions) {
            this.permissions = permissions;
        }
    }



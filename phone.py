# The PhoneDirectory class manages a pool of phone numbers with efficient allocation, release, and availability checks.

# Initialization:
# - Create a list to track the availability of phone numbers.

# get:
# - Find the first available number and mark it as unavailable.
# - Return the number or -1 if none are available.

# check:
# - Return whether a specific number is available.

# release:
# - Mark a specific number as available again.

# TC: 
# - get: O(n) - Scans for the first available number.
# - check/release: O(1) - Direct access based on the number.
# SC: O(n) - Space for the availability list.


class PhoneDirectory:
    def __init__(self, maxNumbers):
        self.is_slot_available = [True] * maxNumbers

    def get(self):
        index = next((i for i, available in enumerate(self.is_slot_available) if available), -1)
        if index != -1:
            self.is_slot_available[index] = False
        return index

    def check(self, number):
        return self.is_slot_available[number]

    def release(self, number):
        self.is_slot_available[number] = True
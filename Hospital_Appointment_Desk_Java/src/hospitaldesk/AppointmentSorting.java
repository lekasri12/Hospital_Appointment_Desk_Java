package hospitaldesk;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSorting {
    private static int compare(Appointment a, Appointment b) {
        int urgencyCompare = Integer.compare(a.getUrgency(), b.getUrgency());
        if (urgencyCompare != 0) return urgencyCompare;
        return a.getArrivalTime().compareTo(b.getArrivalTime());
    }

    public static class SortResult {
        public final List<Appointment> appointments;
        public final long swaps;
        public SortResult(List<Appointment> appointments, long swaps) {
            this.appointments = appointments;
            this.swaps = swaps;
        }
    }

    public static SortResult selectionSort(List<Appointment> input) {
        List<Appointment> items = new ArrayList<>(input);
        long swaps = 0;

        for (int i = 0; i < items.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < items.size(); j++) {
                if (compare(items.get(j), items.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Appointment temp = items.get(i);
                items.set(i, items.get(minIndex));
                items.set(minIndex, temp);
                swaps++;
            }
        }
        return new SortResult(items, swaps);
    }

    public static SortResult quickSort(List<Appointment> input) {
        List<Appointment> items = new ArrayList<>(input);
        long[] swaps = {0};
        quickSort(items, 0, items.size() - 1, swaps);
        return new SortResult(items, swaps[0]);
    }

    private static void quickSort(List<Appointment> items, int low, int high, long[] swaps) {
        if (low < high) {
            int pivotIndex = partition(items, low, high, swaps);
            quickSort(items, low, pivotIndex - 1, swaps);
            quickSort(items, pivotIndex + 1, high, swaps);
        }
    }

    private static int partition(List<Appointment> items, int low, int high, long[] swaps) {
        Appointment pivot = items.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(items.get(j), pivot) <= 0) {
                i++;
                if (i != j) {
                    swap(items, i, j);
                    swaps[0]++;
                }
            }
        }
        if (i + 1 != high) {
            swap(items, i + 1, high);
            swaps[0]++;
        }
        return i + 1;
    }

    private static void swap(List<Appointment> items, int i, int j) {
        Appointment temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }
}

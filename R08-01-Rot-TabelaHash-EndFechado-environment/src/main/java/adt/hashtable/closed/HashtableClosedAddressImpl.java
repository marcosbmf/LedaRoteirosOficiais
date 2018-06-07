package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size (or the given size, if it is already prime). 
    * For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number.
    * If the given number is prime, it is returned. 
    * You can use the method Util.isPrime to check if a number is
    * prime.
    */
   private int getPrimeAbove(int number) {
      number++;
      if (Util.isPrime(number)) {
         return number;
      } else {
         return this.getPrimeAbove(number);
      }
   }

   @Override
   public void insert(T element) {
      int key;
      LinkedList<T> slot;

      key = this.getHash(element);

      if (this.table[key] == null) {
         this.table[key] = new LinkedList<T>();
      } else {
         this.COLLISIONS++;
      }

      slot = this.getSlot(key);
      slot.add(element);
      this.elements++;

   }

   @Override
   public void remove(T element) {
      int key = this.getHash(element);
      if (this.table != null) {
         LinkedList<T> slot = this.getSlot(key);
         if (slot.remove(element)) {
            this.elements--;
            if (!slot.isEmpty()) {
               this.COLLISIONS--;
            }
         }
      }
   }

   @Override
   public T search(T element) {
      int key = this.getHash(element);
      if (this.table[key] == null) {
         return null;
      } else {
         LinkedList<T> slot = this.getSlot(key);
         if (slot.contains(element)) {
            return element;
         } else {
            return null;
         }
      }
   }

   @Override
   public int indexOf(T element) {
      if (element != null) {
         if (element.equals(this.search(element))) {
            return this.getHash(element);
         }
      }
      return -1;
   }

   @SuppressWarnings("unchecked")
   private LinkedList<T> getSlot(int key) {
      if (this.table[key] instanceof LinkedList<?>) {
         return (LinkedList<T>) this.table[key];
      } else {
         throw new ClassCastException("Erro de cast");
      }
   }

   private int getHash(T element) {
      if (this.hashFunction instanceof HashFunctionClosedAddress<?>) {
         return ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
      } else {
         throw new ClassCastException("Erro de cast");
      }
   }

}

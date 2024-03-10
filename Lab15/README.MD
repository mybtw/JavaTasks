 <h2> Логи и их анализ </h2>

<h3> Опция -XX:+PrintCompilation </h3>

     85    1       3       java.lang.Object::<init> (1 bytes)
     86    3       3       java.lang.String::isLatin1 (19 bytes)
     88    5       3       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)
     90    7       3       java.util.ImmutableCollections$SetN::probe (56 bytes)
     92    6       3       java.lang.Math::floorMod (20 bytes)
     94    4       3       java.lang.String::hashCode (60 bytes)
     96   11       3       java.lang.StringLatin1::equals (36 bytes)
     98    8       3       java.lang.StringLatin1::hashCode (52 bytes)
    100   14       3       java.lang.module.ModuleDescriptor::modsHashCode (43 bytes)
    101   12       4       java.lang.Object::<init> (1 bytes)
    102   15       3       java.util.Objects::requireNonNull (14 bytes)
    103    1       3       java.lang.Object::<init> (1 bytes)   made not entrant
    105   16       3       java.lang.String::coder (15 bytes)
    106   13       3       java.util.Set::of (4 bytes)
    108   17       1       java.lang.module.ModuleDescriptor::name (5 bytes)
    109   18       1       java.lang.module.ModuleReference::descriptor (5 bytes)
    110   21       3       java.lang.String::length (11 bytes)
    111    9       3       jdk.internal.util.ArraysSupport::vectorizedHashCode (158 bytes)
    114   10       3       java.lang.String::equals (56 bytes)
    116   30     n 0       jdk.internal.misc.Unsafe::getReferenceVolatile (native)
    117   27       3       java.util.ImmutableCollections$SetN$SetNIterator::next (90 bytes)
    118   32     n 0       jdk.internal.misc.Unsafe::compareAndSetLong (native)
    119   22       3       java.util.ImmutableCollections$SetN$SetNIterator::hasNext (13 bytes)
    120   35       4       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)
    126   33   !   3       java.util.concurrent.ConcurrentHashMap::putVal (432 bytes)
    130   29       3       jdk.internal.misc.Unsafe::getReferenceAcquire (7 bytes)
    131   28       3       java.util.concurrent.ConcurrentHashMap::tabAt (22 bytes)
    132   26       3       java.util.Objects::equals (23 bytes)
    133   36       3       java.util.concurrent.ConcurrentHashMap::addCount (280 bytes)
    135    5       3       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)   made not entrant
    137   39       3       java.util.HashMap::hash (20 bytes)
    138   31       3       java.util.concurrent.ConcurrentHashMap::spread (10 bytes)
    139   42       3       java.util.HashMap::putVal (300 bytes)
    142   43       3       java.util.HashMap::newNode (13 bytes)
    144   44       3       java.util.HashMap$Node::<init> (26 bytes)
    145   41       1       java.lang.module.ModuleDescriptor$Exports::source (5 bytes)
    146   20       3       jdk.internal.util.Preconditions::checkIndex (18 bytes)
    147   50     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLLLLLL)L (native)   (static)
    148    2       3       java.lang.String::charAt (25 bytes)
    149   19       3       java.lang.String::checkIndex (10 bytes)
    150   45       3       java.util.HashMap::afterNodeInsertion (1 bytes)
    151   46       3       java.util.HashMap::put (13 bytes)
    151   51     n 0       java.lang.Object::hashCode (native)
    152   38       1       java.lang.module.ResolvedModule::reference (5 bytes)
    153   52     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LL)L (native)   (static)
    154   34       3       java.util.concurrent.ConcurrentHashMap$Node::<init> (20 bytes)
    155   37       3       java.util.concurrent.ConcurrentHashMap::putIfAbsent (8 bytes)
    155   47       1       java.lang.module.ResolvedModule::configuration (5 bytes)
    156   54     n 0       java.lang.invoke.MethodHandle::invokeBasic(LLLLLL)L (native)
    157   49       1       java.util.ImmutableCollections$Set12::isEmpty (2 bytes)
    158   55     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLLLLL)L (native)   (static)
    159   25       3       java.util.AbstractCollection::<init> (5 bytes)
    160   56     n 0       java.lang.System::arraycopy (native)   (static)
    161   48       1       java.lang.module.ModuleDescriptor$Exports::targets (5 bytes)
    162   23       1       java.util.KeyValueHolder::getKey (5 bytes)
    163   40       1       java.lang.module.ModuleDescriptor::isAutomatic (5 bytes)
    164   24       1       java.util.KeyValueHolder::getValue (5 bytes)
    166   58     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLL)V (native)   (static)
    167   57       1       java.lang.Enum::ordinal (5 bytes)
    169   53       1       java.lang.module.ModuleDescriptor::isOpen (5 bytes)
    169   59     n 0       java.lang.invoke.MethodHandle::invokeBasic(L)L (native)
    170   60     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLL)L (native)   (static)
    173   61       3       java.lang.String::isEmpty (14 bytes)
    173   62     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLL)L (native)   (static)
    175   63       3       java.lang.StringLatin1::canEncode (13 bytes)
    177   64     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLL)V (native)   (static)
    178   65     n 0       java.lang.invoke.MethodHandle::invokeBasic(LL)L (native)   
    179   66     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLL)L (native)   (static)
    183   67       1       java.lang.invoke.MethodType::returnType (5 bytes)
    183   68     n 0       java.lang.Class::isPrimitive (native)
    184   69     n 0       java.lang.invoke.MethodHandle::linkToStatic(L)L (native)   (static)
    186   70       3       java.lang.Math::min (11 bytes)
    187   71     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LL)V (native)   (static)
    188   72     n 0       java.lang.invoke.MethodHandle::invokeBasic()L (native)
    189   73       3       java.lang.invoke.MethodTypeForm::canonicalize (74 bytes)
    191   74     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLLL)L (native)   (static)
    193   75       1       java.lang.invoke.MemberName::getDeclaringClass (5 bytes)
    194   76       3       java.lang.AbstractStringBuilder::isLatin1 (19 bytes)
    195   77       3       java.util.concurrent.ConcurrentHashMap::get (162 bytes)
    198   78     n 0       jdk.internal.misc.Unsafe::compareAndSetReference (native)   
    200   79       3       java.util.concurrent.ConcurrentHashMap::casTabAt (21 bytes)
    201   80 %     3       java.util.Properties$LineReader::readLine @ 224 (584 bytes)
    202   81     n 0       java.lang.invoke.MethodHandle::linkToStatic(LL)L (native)   (static)
    205   86       3       java.lang.AbstractStringBuilder::ensureCapacityInternal (39 bytes)
    205   87     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLL)V (native)   (static)
    206   84       1       java.lang.AbstractStringBuilder::getValue (5 bytes)
    207   89     n 0       java.lang.invoke.MethodHandle::invokeBasic(LLL)L (native)
    208   85       1       java.lang.AbstractStringBuilder::length (5 bytes)
    208   90     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLL)L (native)   (static)
    209   82       3       java.lang.invoke.MemberName::isInvocable (8 bytes)
    210   83       3       java.lang.invoke.MemberName::anyFlagSet (15 bytes)
    211   88       1       java.lang.invoke.MethodType::form (5 bytes)
    211   91       3       java.lang.StringLatin1::indexOfChar (33 bytes)
    212   92       3       java.lang.Math::max (11 bytes)
    213   93       3       java.lang.String::indexOf (37 bytes)
    214   94       3       java.lang.ref.ReferenceQueue::headIsNull (13 bytes)
    216   95       3       java.lang.StringBuilder::append (8 bytes)
    217   96       3       java.lang.AbstractStringBuilder::append (45 bytes)
    218   99       3       java.lang.AbstractStringBuilder::append (77 bytes)
    220   98       3       java.lang.StringBuilder::append (8 bytes)
    221  100       4       java.lang.String::charAt (25 bytes)
    221   97       1       java.lang.invoke.MethodType::ptypes (5 bytes)
    222  102       3       sun.nio.cs.SingleByte$Encoder::encode (32 bytes)


Каждая строка лога представляет собой отдельный метод, который был скомпилирован, и содержит следующие сведения:

Время начала компиляции: показывает время (в миллисекундах от старта JVM), когда началась компиляция метода.
ID компиляции: уникальный номер, присвоенный процессу компиляции.
Уровень оптимизации: уровень оптимизации, применяемый к методу. Уровни могут варьироваться, но обычно 3 означает полную оптимизацию, а 1 — меньшую степень оптимизации.
Полное имя метода: включает имя класса, имя метода, и, возможно, параметры или спецификации метода.
Размер байт-кода: количество байтов в байт-коде метода.
Дополнительные флаги: например, n указывает на native-метод, ! означает, что метод является критически важным и требует особого внимания при оптимизации.
Некоторые ключевые наблюдения из вашего лога:

Частая компиляция: Методы java.lang.String::isLatin1, java.util.ImmutableCollections$SetN::probe, и java.lang.Math::floorMod были оптимизированы на раннем этапе выполнения, что указывает на их активное использование.
Компиляция native-методов: Методы с флагом n, например jdk.internal.misc.Unsafe::getReferenceVolatile, являются нативными и были специально обработаны JIT-компилятором для увеличения производительности.
Перекомпиляция: Некоторые методы, например java.lang.Object::<init>, были скомпилированы несколько раз, что может указывать на дальнейшую оптимизацию на основе профилирования во время выполнения.
Оптимизация горячих точек: Методы, такие как java.util.concurrent.ConcurrentHashMap::putVal, которые имеют большой размер байт-кода и компилируются на высоком уровне оптимизации, являются критически важными для производительности и были оптимизированы для повышения эффективности.

<h3> Опция -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining</h3>

     71    1       3       java.lang.Object::<init> (1 bytes)                               
     72    5       3       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)       
     74    7       3       java.util.ImmutableCollections$SetN::probe (56 bytes)            
                              @ 1   java.lang.Object::hashCode (0 bytes)   no static binding
                              @ 9   java.lang.Math::floorMod (20 bytes)   inline            
                              @ 31   java.lang.Object::equals (11 bytes)   no static binding
     78   12       3       java.lang.StringLatin1::equals (36 bytes)
     80    6       3       java.lang.Math::floorMod (20 bytes)
     81    3       3       java.lang.String::isLatin1 (19 bytes)
     83   14       3       java.util.Objects::requireNonNull (14 bytes)
     84   10       4       java.lang.Object::<init> (1 bytes)
                              @ 8   java.lang.NullPointerException::<init> (5 bytes)   don't inline Throwable constructors
     86   13       3       java.util.Set::of (4 bytes)
     87    1       3       java.lang.Object::<init> (1 bytes)   made not entrant
     88   11       3       java.lang.String::equals (56 bytes)
                              @ 44   java.lang.StringLatin1::equals (36 bytes)   callee is too large
     92   22       3       java.util.ImmutableCollections$SetN$SetNIterator::next (90 bytes)
     93   25     n 0       jdk.internal.misc.Unsafe::getReferenceVolatile (native)
                              @ 86  java/util/NoSuchElementException::<init> (not loaded)   not inlineable
     95   27     n 0       jdk.internal.misc.Unsafe::compareAndSetLong (native)
     96   20       3       java.util.ImmutableCollections$SetN$SetNIterator::hasNext (13 bytes)
     96   30       4       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)
     97   28   !   3       java.util.concurrent.ConcurrentHashMap::putVal (432 bytes)
                              @ 17   java.lang.Object::hashCode (0 bytes)   no static binding
                              @ 20   java.util.concurrent.ConcurrentHashMap::spread (10 bytes)   inline
    102    5       3       jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)   made not entrant
                              @ 69   java.util.concurrent.ConcurrentHashMap::tabAt (22 bytes)   callee uses too much stack
                              @ 91   java.util.concurrent.ConcurrentHashMap$Node::<init> (20 bytes)   inline
                                @ 1   java.lang.Object::<init> (1 bytes)   inline
                              @ 94   java.util.concurrent.ConcurrentHashMap::casTabAt (21 bytes)   callee uses too much stack
                              @ 120   java.util.concurrent.ConcurrentHashMap::helpTransfer (124 bytes)   callee is too large
                              @ 159   java.lang.Object::equals (11 bytes)   no static binding
                              @ 192   java.util.concurrent.ConcurrentHashMap::tabAt (22 bytes)   callee uses too much stack
                              @ 242   java.lang.Object::equals (11 bytes)   no static binding
                              @ 293   java.util.concurrent.ConcurrentHashMap$Node::<init> (20 bytes)   inline
                                @ 1   java.lang.Object::<init> (1 bytes)   inline
                              @ 331  java/util/concurrent/ConcurrentHashMap$TreeBin::putTreeVal (not loaded)   not inlineable
                              @ 374  java/lang/IllegalStateException::<init> (not loaded)   not inlineable
               !m             @ 409   java.util.concurrent.ConcurrentHashMap::treeifyBin (164 bytes)   callee is too large
                              @ 427   java.util.concurrent.ConcurrentHashMap::addCount (280 bytes)   callee is too large
               !              @ 49   java.util.concurrent.ConcurrentHashMap::initTable (112 bytes)   callee is too large
                              @ 12   java.lang.NullPointerException::<init> (5 bytes)   don't inline Throwable constructors
    121   39     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLLLLLL)L (native)   (static)
    124   37       3       java.util.HashMap::putVal (300 bytes)
                              @ 20   java.util.HashMap::resize (356 bytes)   callee is too large
                              @ 56   java.util.HashMap::newNode (13 bytes)   callee uses too much stack
                              @ 91   java.lang.Object::equals (11 bytes)   no static binding
    129   40     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LL)L (native)   (static)
                              @ 123   java.util.HashMap$TreeNode::putTreeVal (314 bytes)   callee's klass not linked yet
                              @ 152   java.util.HashMap::newNode    132   42     n 0       java.lang.invoke.MethodHandle::invokeBasic(LLLLLL)L (native)
    (13 bytes)    133   43     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLLLLL)L (native)   (static)
    callee uses too much stack
    @ 169   java.util.HashMap::treeifyBin (110 bytes)   callee is too large
    @ 203   java.lang.Object::equals (11 bytes)   no static binding
    @ 253   java.util.HashMap::afterNodeAccess    138   45     n 0       java.lang.Object::hashCode (native)
    (1 bytes)   inline
    139   46     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLL)V (native)   (static)
    @ 288   java.util.HashMap::resize (356 bytes)   callee is too large
    @ 295   java.util.HashMap::afterNodeInsertion (1 bytes)   inline
    142   47     n 0       java.lang.invoke.MethodHandle::invokeBasic(L)L (native)   
    144   48     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLL)L (native)   (static)
    145   17       1       java.lang.module.ModuleReference::descriptor (5 bytes)
    146    2       3       java.lang.String::charAt (25 bytes)
    @ 1   java.lang.String::isLatin1 (19 bytes)   inline
    147   49     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLL)L (native)   (static)
    @ 12   java.lang.StringLatin1::charAt (15 bytes)   inline
    @ 3   java.lang.String::checkIndex (10 bytes)   inline
    151   53     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLL)V (native)   (static)
    152   54     n 0       java.lang.invoke.MethodHandle::invokeBasic(LL)L (native)   
    @ 5   jdk.internal.util.Preconditions::checkIndex (18 bytes)   intrinsic
    154   55     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLL)L (native)   (static)
    @ 21   java.lang.StringUTF16::charAt (11 bytes)   inline
    @ 2   java.lang.StringUTF16::checkIndex (9 bytes)   inline
    @ 2   java.lang.StringUTF16::    158   57     n 0       java.lang.invoke.MethodHandle::linkToStatic(L)L (native)   (static)
    length (5 bytes)   inline
    159   58     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LL)V (native)   (static)
    @ 5   java.lang.String::checkIndex (10 bytes)   inline
    161   59     n 0       java.lang.invoke.MethodHandle::invokeBasic()L (native)
    @ 5   jdk.internal.util.Preconditions::checkIndex (18 bytes)   intrinsic
    @ 7   java.lang.StringUTF16::getChar (60 bytes)   intrinsic
    164   60     n 0       java.lang.invoke.MethodHandle::linkToStatic(LLLL)L (native)   (static)
    165   50       3       jdk.internal.util.Preconditions::checkIndex (18 bytes)
    @ 12   jdk.internal.util.Preconditions::outOfBoundsCheckIndex (25 bytes)   callee uses too much stack
    167   51       3       java.lang.String::checkIndex (10 bytes)
    @ 5   jdk.internal.util.Preconditions::checkIndex (18 bytes)   intrinsic
    169   52       3       java.lang.StringLatin1::charAt (15 bytes)
    @ 3   java.lang.String::checkIndex (10 bytes)   inline
    @ 5   jdk.internal.util.Preconditions::checkIndex (18 bytes)   intrinsic
    172   23       3       java.util.concurrent.ConcurrentHashMap::tabAt (22 bytes)
    @ 15   jdk.internal.misc.Unsafe::getReferenceAcquire (7 bytes)   inline
    @ 3   jdk.internal.misc.Unsafe::getReferenceVolatile (0 bytes)   intrinsic
    174   62     n 0       jdk.internal.misc.Unsafe::compareAndSetReference (native)
    175   24       3       jdk.internal.misc.Unsafe::getReferenceAcquire (7 bytes)
    @ 3   jdk.internal.misc.Unsafe::getReferenceVolatile (0 bytes)   intrinsic
    178   64     n 0       java.lang.invoke.MethodHandle::linkToStatic(LL)L (native)   (static)
    178   63       3       java.util.concurrent.ConcurrentHashMap::casTabAt (21 bytes)
    @ 17   jdk.internal.misc.Unsafe::compareAndSetReference (0 bytes)   intrinsic
    180   16       3       java.lang.String::coder (15 bytes)
    181   67       3       java.lang.String::length (11 bytes)
    @ 6   java.lang.String::coder (15 bytes)   inline
    183   68     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLL)V (native)   (static)
    184    8       3       java.lang.StringLatin1::hashCode (52 bytes)
    185   70     n 0       java.lang.System::arraycopy (native)   (static)
    185   71     n 0       java.lang.invoke.MethodHandle::invokeBasic(LLL)L (native)
    @ 48   jdk.internal.util.ArraysSupport::    187   72     n 0       java.lang.invoke.MethodHandle::linkToSpecial(LLLLL)L (native)   (static)
    vectorizedHashCode (158 bytes)   callee is too large
    188    9       3       jdk.internal.util.ArraysSupport::vectorizedHashCode (158 bytes)
    @ 51   jdk.internal.util.ArraysSupport::signedHashCode (37 bytes)   callee is too large
    @ 71   jdk.internal.util.ArraysSupport::utf16hashCode (40 bytes)   callee is too large
    @ 84   jdk.internal.util.ArraysSupport::hashCode (33 bytes)   callee uses too much stack
    @ 97   jdk.internal.util.ArraysSupport::hashCode (33 bytes)   callee uses too much stack
    @ 110   jdk.internal.util.ArraysSupport::hashCode (33 bytes)   callee uses too much stack
    @ 123   jdk.internal.util.ArraysSupport::hashCode (33 bytes)   callee uses too much stack
    @ 137   java.lang.StringBuilder::<init> (7 bytes)   inline
    @ 3   java.lang.AbstractStringBuilder::<init> (39 bytes)   callee is too large
    @ 142   java.lang.StringBuilder::append (8 bytes)    198   80       4       java.lang.String::charAt (25 bytes)
    inline
    @ 2   java.lang.AbstractStringBuilder::append (45 bytes)   callee is too large
    @ 147   java.lang.StringBuilder::append (8 bytes)    201    2       3       java.lang.String::charAt (25 bytes)   made not entrant
    inline                              @ 1   java.lang.String::isLatin1 (19 bytes)   inline (hot)
    @ 12   java.lang.StringLatin1::charAt (15 bytes)   inline (hot)
    @ 3   java.lang.String::checkIndex (10 bytes)   inline (hot)
    @ 5   jdk.internal.util.Preconditions::checkIndex (18 bytes)   (intrinsic)
    @ 21   java.lang.StringUTF16::charAt (11 bytes)   low call site frequency

    203   81       4       java.lang.String::length (11 bytes)
    
                                @ 2   java.lang.AbstractStringBuilder::append (55 bytes)   callee is too large
    214   67       3       java.lang.String::length (11 bytes)   made not entrant
                              @ 150   java.lang.StringBuilder::toString (9 bytes)   inline
                              @ 6   java.lang.String::coder (15 bytes)   inline (hot)


После каждой записи о компиляции метода идут детали о попытках встраивания других методов в этот метод. Вот ключевые моменты, на которые стоит обратить внимание при анализе этих логов:

Метод и его компиляция: Указывается имя метода, уровень оптимизации и размер байт-кода. Например, java.util.ImmutableCollections$SetN::probe (56 bytes) говорит о том, что метод probe из класса ImmutableCollections$SetN был скомпилирован, имея размер байт-кода 56 байт.

Встраивание (Inlining): После каждой записи о компиляции идут записи о попытках встраивания методов. Встраивание - это ключевая оптимизация, при которой вызов метода заменяется на его тело, что снижает накладные расходы на вызов метода. Например:

@ 9 java.lang.Math::floorMod (20 bytes) inline говорит, что метод floorMod из класса Math был встроен.
@ 1 java.lang.Object::hashCode (0 bytes) no static binding указывает на попытку встраивания метода hashCode класса Object, которая не удалась из-за отсутствия статической привязки.
Принятие решений о встраивании:

inline: Метод был успешно встроен.
no static binding: Невозможно встроить из-за динамической природы вызова.
callee is too large: Метод не был встроен из-за его большого размера.
don't inline Throwable constructors: Конструкторы исключений специально не встраиваются для сохранения точного стека вызовов при исключениях.
not inlineable: Метод не может быть встроен по какой-либо причине, например, из-за его сложности или специфики.
callee uses too much stack: указывает на то, что метод, который предполагалось встроить, потребляет слишком много стековой памяти. Встраивание такого метода может привести к риску переполнения стека, особенно если встраиваемый метод вызывается рекурсивно или находится в глубокой цепочке вызовов.
made not entrant: указывает на то, что ранее скомпилированный код метода больше не используется и может быть замещён новой версией кода.
intrinsic - это оптимизированные нативные реализации методов готовые к использованию JVM. Обычно это базовые, критичные к производительности методы, использующие специфичные функции операционной системы (ОС) или архитектуры процессора. Из-за этого они являются платформо-зависимыми и некоторые из них могут поддерживаться не каждой платформой.
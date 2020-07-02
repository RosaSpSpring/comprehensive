package com.example.ble;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	private BluetoothAdapter adapter;
	private BluetoothGatt bluetoothGatt;


	private boolean mScanning;
	private Handler handler;

	private UUID suuid;
	private UUID cuuid;


	// Stops scanning after 10 seconds.
	private static final long SCAN_PERIOD = 10000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);
		//        initTest();
		Button button = findViewById( R.id.blueButton );
		Button send = findViewById( R.id.send );
		Button read = findViewById( R.id.read );

		button.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// Code here executes on main thread after user presses button
				//                        startScan();



				startActivity( new Intent( MainActivity.this, ShotScreen.class ) );
			}
		} );


		send.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// Code here executes on main thread after user presses button
				sendMessage( suuid, cuuid );

			}
		} );

		read.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// Code here executes on main thread after user presses button
				readMessage( suuid, cuuid );

			}
		} );


		//sendMessage(service.getUuid(), character.getUuid());

//		openBlueToothLe();

	}

	private void initTest() {
		// 测试关于 string
		String testStringIndexOf = "";
		String testStringSeparator = "";
		testStringSeparator = getResources().getString( R.string.string_separator );
		testStringIndexOf = getResources().getString( R.string.string_index_of );
		int i = testStringIndexOf.indexOf( testStringSeparator );
		Log.e( TAG, "i = " + i );

		String format1 = getResources().getString( R.string.format1 );

		String format = getResources().getString( R.string.format );
		String subtitle1 = String.format( format1, 21.2222f, 47 );
		String subtitle = String.format( format, 20f, 45 );
		// 打印主题标题文字
		Log.e( TAG, "subtitle = " + subtitle );
		Log.e( TAG, "subtitle1" + subtitle1 );

	}

	public void readMessage(UUID serviceUUid, UUID charUUid) {
		BluetoothGattService gattService = bluetoothGatt.getService( serviceUUid );
		BluetoothGattCharacteristic gattCharacteristic = gattService.getCharacteristic( charUUid );
		bluetoothGatt.readCharacteristic( gattCharacteristic );


	}

	public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
		//        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
		//            Log.w(TAG, "BluetoothAdapter not initialized");
		//            return;
		//        }
		//        mBluetoothGatt.readCharacteristic(characteristic);
		//        Log.d("CharacteristicRead", String.valueOf(characteristic)); // try to get characteristic uuid?
		//        Log.d("BLE_VALUE_READ", String.valueOf(characteristic.getValue())); // Try to read value
	}

	//    private LeDeviceListAdapter leDeviceListAdapter;

	// Device scan callback.
	//    private BluetoothAdapter.LeScanCallback leScanCallback =
	//            new BluetoothAdapter.LeScanCallback() {
	//                @Override
	//                public void onLeScan(final BluetoothDevice device, int rssi,
	//                                     byte[] scanRecord) {
	//                    runOnUiThread(new Runnable() {
	//                        @Override
	//                        public void run() {
	//                            leDeviceListAdapter.addDevice(device);
	//                            leDeviceListAdapter.notifyDataSetChanged();
	//                        }
	//                    });
	//                }
	//            };


	//    private void scanLeDevice(final boolean enable) {
	//        if (enable) {
	//            // Stops scanning after a pre-defined scan period.
	//            handler.postDelayed(new Runnable() {
	//                @Override
	//                public void run() {
	//                    mScanning = false;
	//                    adapter.stopLeScan(leScanCallback);
	//                }
	//            }, SCAN_PERIOD);
	//
	//            mScanning = true;
	//            adapter.startLeScan(leScanCallback);
	//        } else {
	//            mScanning = false;
	//            adapter.stopLeScan(leScanCallback);
	//        }
	//    }

	//打开蓝牙
	private void openBlueToothLe() {
		adapter = BluetoothAdapter.getDefaultAdapter();
		if (null == adapter) {
			Toast.makeText( this, "没有蓝牙功能", Toast.LENGTH_SHORT ).show();
			return;
		} else {
			Toast.makeText( this, "有蓝牙功能", Toast.LENGTH_SHORT ).show();
		}
		if (!adapter.isEnabled()) {
			adapter.enable();
		}
		//        startScan();
	}

	//开始扫描
	private void startScan() {

		adapter.startLeScan( mLeScanCallback );
		//        Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
		//        for (BluetoothDevice bondedDevice : bondedDevices) {
		//            if ("你想要接收数据的已配对设备名称".equals(bondedDevice.getName().trim())) {
		//                connectDevice(bondedDevice);
		//            }
		//        }
	}

	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
		@Override
		public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
			//The code here is executed on main thread

			//                    Log.e("LeScanCallback", Thread.currentThread().getName());//Prints main
			if (device.getName() != null) {
				if (device.getName().startsWith( "JDY" )) {
					connectDevice( device );
				}

				Log.e( "LeScanCallback", device.getName() );
				Log.e( "LeScanCallback", device.getAddress() );
			}

		}
	};

	public void sendMessage(UUID serviceUUid, UUID charUUid) {

		byte[] byteData = hexToBytes( "68127502800187cf16" );
		byte[] writeData = new byte[byteData.length];
		for (int i = 0; i < byteData.length; i++) {
			writeData[i] = byteData[i];
		}

		BluetoothGattService gattService = bluetoothGatt.getService( serviceUUid );
		BluetoothGattCharacteristic gattCharacteristic = gattService.getCharacteristic( charUUid );

		gattCharacteristic.setValue( writeData );
		System.out.println( "send 68127502800187cf16" );

		boolean status = bluetoothGatt.writeCharacteristic( gattCharacteristic );
		System.out.println( "status " + status );
	}

	public static String bytesToHex(byte[] in) {
		final StringBuilder builder = new StringBuilder();
		for (byte b : in) {
			builder.append( String.format( "%02x", b ) );
		}
		return builder.toString();
	}


	public byte[] hexToBytes(String hexRepresentation) {
		int len = hexRepresentation.length();
		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit( hexRepresentation.charAt( i ), 16 ) << 4) + Character.digit( hexRepresentation.charAt( i + 1 ), 16 ));
		}
		return data;
	}

	//连接设备
	private void connectDevice(BluetoothDevice bondedDevice) {
		bluetoothGatt = bondedDevice.connectGatt( this, false, new BluetoothGattCallback() {
			@Override
			public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
				super.onConnectionStateChange( gatt, status, newState );

				if (BluetoothGatt.STATE_CONNECTED == newState) {
					bluetoothGatt = gatt;
					gatt.discoverServices();
					//                    sendMessage();
				} else if (BluetoothGatt.STATE_DISCONNECTED == newState) {
					gatt.close();
				}
			}


			@Override
			public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
				super.onCharacteristicWrite( gatt, characteristic, status );
				System.out.println( "写入完成" );
			}


			public void sendMessage() {

				byte[] byteData = hexToBytes( "68127502800187cf16" );
				byte[] writeData = new byte[byteData.length];
				for (int i = 0; i < byteData.length; i++) {
					writeData[i] = byteData[i];
				}

				BluetoothGattService gattService = bluetoothGatt.getService( suuid );
				BluetoothGattCharacteristic gattCharacteristic = gattService.getCharacteristic( cuuid );


				gattCharacteristic.setValue( writeData );
				//                System.out.println("send 68127502800187cf16");

				bluetoothGatt.writeCharacteristic( gattCharacteristic );
			}

			@Override
			public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
				super.onCharacteristicRead( gatt, characteristic, status );
				System.out.println( "BluetoothGattCharacteristic" + characteristic.getValue() );
			}

			@Override
			public void onServicesDiscovered(BluetoothGatt gatt, int status) {
				super.onServicesDiscovered( gatt, status );

				List<BluetoothGattService> services = gatt.getServices();
				if (gatt.getDevice().getName().startsWith( "JDY" )) {
					//                    gatt.discoverServices();
					adapter.stopLeScan( mLeScanCallback );
				}
				for (BluetoothGattService service : services) {
					List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();

					for (BluetoothGattCharacteristic character : characteristics) {
						if (gatt.getDevice().getName().startsWith( "JDY" ) && character.getUuid().toString().startsWith( "0000ffe1" )) {
							// &&character.getUuid().toString().startsWith("0000ffe1")

							suuid = service.getUuid();
							cuuid = character.getUuid();
							System.out.println( "suuid" + suuid );
							System.out.println( "cuuid" + cuuid );
							if (enableNotification( gatt, service.getUuid(), character.getUuid() )) {
								System.out.println( "enableNotification开启" );
							} else {
								System.out.println( "enableNotification没开启" );
							}
						}
					}
				}
			}

			@Override
			public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
				super.onCharacteristicChanged( gatt, characteristic );
				System.out.println( "onCharacteristicChanged" );
				byte[] value = characteristic.getValue();
				Log.i( "BLE", "receive value ---------" );
				//                for (int i = 0; i < value.length; i++) {
				//                    Log.i("BLE", "character_value = " + value[i]);
				//                }
				System.out.println( bytesToHex( value ) );

			}
		} );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		bluetoothGatt.disconnect();
	}


	public boolean enableNotification(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID) {
		boolean success = false;
		BluetoothGattService service = gatt.getService( serviceUUID );
		if (service != null) {
			BluetoothGattCharacteristic characteristic = findNotifyCharacteristic( service, characteristicUUID );
			if (characteristic != null) {
				success = gatt.setCharacteristicNotification( characteristic, true );
				if (success) {
					// 来源：http://stackoverflow.com/questions/38045294/oncharacteristicchanged-not-called-with-ble
					for (BluetoothGattDescriptor dp : characteristic.getDescriptors()) {
						if (dp != null) {
							if ((characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0) {
								dp.setValue( BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE );
							} else if ((characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0) {
								dp.setValue( BluetoothGattDescriptor.ENABLE_INDICATION_VALUE );
							}
							gatt.writeDescriptor( dp );

						}
					}
				}
			}
		}
		System.out.println( "last" + success );
		return success;
	}

	private BluetoothGattCharacteristic findNotifyCharacteristic(BluetoothGattService service, UUID characteristicUUID) {
		BluetoothGattCharacteristic characteristic = null;
		List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
		for (BluetoothGattCharacteristic c : characteristics) {
			if ((c.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0 && characteristicUUID.equals( c.getUuid() )) {
				characteristic = c;
				break;
			}
		}

		if (characteristic != null)
			return characteristic;
		for (BluetoothGattCharacteristic c : characteristics) {
			System.out.println( "length" + characteristics.size() );
			if ((c.getProperties() & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0 && characteristicUUID.equals( c.getUuid() )) {
				characteristic = c;
				break;
			}
		}
		return characteristic;
	}

	//    // Adapter for holding devices found through scanning.
	//    private class LeDeviceListAdapter extends BaseAdapter {
	//        private ArrayList<BluetoothDevice> mLeDevices;
	//        private LayoutInflater mInflator;
	//        public LeDeviceListAdapter() {
	//            super();
	//            mLeDevices = new ArrayList<BluetoothDevice>();
	//            mInflator = DeviceScanActivity.this.getLayoutInflater();
	//        }
	//        public void addDevice(BluetoothDevice device) {
	//            if(!mLeDevices.contains(device)) {
	//                mLeDevices.add(device);
	//            }
	//        }
	//        public BluetoothDevice getDevice(int position) {
	//            return mLeDevices.get(position);
	//        }
	//        public void clear() {
	//            mLeDevices.clear();
	//        }
	//        @Override
	//        public int getCount() {
	//            return mLeDevices.size();
	//        }
	//        @Override
	//        public Object getItem(int i) {
	//            return mLeDevices.get(i);
	//        }
	//        @Override
	//        public long getItemId(int i) {
	//            return i;
	//        }
	//        @Override
	//        public View getView(int i, View view, ViewGroup viewGroup) {
	//            ViewHolder viewHolder;
	//            // General ListView optimization code.
	//            if (view == null) {
	//                view = mInflator.inflate(R.layout.listitem_device, null);
	//                viewHolder = new ViewHolder();
	//                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
	//                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
	//                view.setTag(viewHolder);
	//            } else {
	//                viewHolder = (ViewHolder) view.getTag();
	//            }
	//            BluetoothDevice device = mLeDevices.get(i);
	//            final String deviceName = device.getName();
	//            if (deviceName != null && deviceName.length() > 0)
	//                viewHolder.deviceName.setText(deviceName);
	//            else
	//                viewHolder.deviceName.setText(R.string.unknown_device);
	//            viewHolder.deviceAddress.setText(device.getAddress());
	//            return view;
	//        }
	//    }
}


class BytesUtil {
	public static int bytes2Int(byte[] bytes) {
		int result = 0;
		//将每个byte依次搬运到int相应的位置
		result = bytes[0] & 0xff;
		result = result << 8 | bytes[1] & 0xff;
		result = result << 8 | bytes[2] & 0xff;
		result = result << 8 | bytes[3] & 0xff;
		return result;
	}

	public static byte[] int2Bytes(int num) {
		byte[] bytes = new byte[4];
		//通过移位运算，截取低8位的方式，将int保存到byte数组
		bytes[0] = (byte) (num >>> 24);
		bytes[1] = (byte) (num >>> 16);
		bytes[2] = (byte) (num >>> 8);
		bytes[3] = (byte) num;
		return bytes;
	}


}
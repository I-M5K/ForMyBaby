
from edge_LP import NetworkManager


def main():
    edge_lp_config = {
        "ip": "192.168.100.58",
        # "ip": "192.168.100.130",
        "port": 3001,
        "baby_id": "1"
    }

    # Init NetworkManager
    network_manager = NetworkManager(**edge_lp_config)


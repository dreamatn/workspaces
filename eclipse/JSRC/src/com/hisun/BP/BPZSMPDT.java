package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPDT {
    String CPN_PDTP_MT = "BP-R-MAINT-PRD-TYPE ";
    String CPN_PDME_BROWER = "BP-T-MAINT-PRDT-MENU";
    String CPN_PDTP_BROWER = "BP-R-BRW-PRD-TYPE   ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "PDT  INFOMATION MAINTAIN";
    String K_CPY_BPRPDTP = "BPRPDTP";
    String WS_ERR_MSG = " ";
    int WS_OPEN_DATE = 0;
    char WS_BOTTOM_IND = ' ';
    String WS_PRDT_TYPE = " ";
    String WS_SUPR_TYPE = " ";
    String WS_DESC = " ";
    String WS_DESC_ENG = " ";
    String WS_SUPR_DESC = " ";
    String WS_SUPR_DESC_ENG = " ";
    BPZSMPDT_WS_OUT_DATA WS_OUT_DATA = new BPZSMPDT_WS_OUT_DATA();
    int WS_COUNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPRPDTP BPROPDTP = new BPRPDTP();
    BPRPDME BPRPDME = new BPRPDME();
    BPCRMPDT BPCRMPDT = new BPCRMPDT();
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    BPCTPDME BPCTPDME = new BPCTPDME();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCO702 BPCO702 = new BPCO702();
    SCCGWA SCCGWA;
    BPCSMPDT BPCSMPDT;
    public void MP(SCCGWA SCCGWA, BPCSMPDT BPCSMPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPDT = BPCSMPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSMPDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCSMPDT.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
        } else if (BPCSMPDT.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
        } else if (BPCSMPDT.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
        } else if (BPCSMPDT.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B060_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMPDT.PRDT_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'Q';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMPDT.BOTTOM_IND != 'T' 
            && BPCSMPDT.SUPR_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_SUPR_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMPDT.SUPR_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPDT);
            BPCPQPDT.PRDT_TYPE = BPCSMPDT.SUPR_TYPE;
            S000_CALL_BPZPQPDT();
            if (BPCPQPDT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SUPR_TYPE_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPCSMPDT.BOTTOM_IND == 'M' 
                || BPCSMPDT.BOTTOM_IND == 'B') {
                if (BPCPQPDT.BOTTOM_IND == 'B') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SUPR_LEVEL_NOT_T_M;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        WS_PRDT_TYPE = BPCPQPDT.PRDT_TYPE;
        if (BPCPQPDT.BOTTOM_IND == 'B') {
            B041_CHECK_PDT_MENU();
            B051_MODIFY_SUPR_TYPE();
        }
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'A';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPRPDTP.BOTTOM_IND = BPCSMPDT.BOTTOM_IND;
        BPRPDTP.SUPR_TYPE = BPCSMPDT.SUPR_TYPE;
        BPRPDTP.DESC = BPCSMPDT.DESC;
        BPRPDTP.DESC_ENG = BPCSMPDT.DESC_ENG;
        BPRPDTP.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPDTP.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPDTP.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDT.PRDT_TYPE;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPDTP;
        S000_CALL_BPZPNHIS();
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMPDT.BOTTOM_IND != 'T' 
            && BPCSMPDT.SUPR_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_SUPR_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMPDT.SUPR_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPDT);
            BPCPQPDT.PRDT_TYPE = BPCSMPDT.SUPR_TYPE;
            S000_CALL_BPZPQPDT();
            if (BPCPQPDT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SUPR_TYPE_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPCSMPDT.BOTTOM_IND == 'M' 
                || BPCSMPDT.BOTTOM_IND == 'B') {
                CEP.TRC(SCCGWA, "T AND M WRONY CHOOSE");
                if (BPCPQPDT.BOTTOM_IND == 'B') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SUPR_LEVEL_NOT_T_M;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'R';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
        IBS.CLONE(SCCGWA, BPRPDTP, BPROPDTP);
        WS_OPEN_DATE = BPRPDTP.OPEN_DATE;
        if (BPRPDTP.BOTTOM_IND == 'B' 
            && BPCSMPDT.BOTTOM_IND != 'B') {
            B041_CHECK_PDT_MENU();
        }
        if (BPRPDTP.BOTTOM_IND != 'B' 
            && BPCSMPDT.BOTTOM_IND == 'B') {
            B042_CHECK_PDT_SUB();
        }
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'U';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPRPDTP.BOTTOM_IND = BPCSMPDT.BOTTOM_IND;
        BPRPDTP.SUPR_TYPE = BPCSMPDT.SUPR_TYPE;
        BPRPDTP.DESC = BPCSMPDT.DESC;
        BPRPDTP.DESC_ENG = BPCSMPDT.DESC_ENG;
        BPRPDTP.OPEN_DATE = WS_OPEN_DATE;
        BPRPDTP.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPDTP.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDT.PRDT_TYPE;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPDTP;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROPDTP;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPDTP;
        S000_CALL_BPZPNHIS();
    }
    public void B041_CHECK_PDT_MENU() throws IOException,SQLException,Exception {
        B041_01_START_BROWSE();
        while (BPCTPDME.RETURN_INFO != 'N') {
            B041_02_READ_NEXT();
            if (BPCTPDME.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYPE_HAVE_PRODUC;
                S000_ERR_MSG_PROC();
            }
        }
        B041_03_END_BROWSE();
    }
    public void B042_CHECK_PDT_SUB() throws IOException,SQLException,Exception {
        B042_01_START_BROWSE();
        while (BPCRBPDT.RETURN_INFO != 'N') {
            B042_02_READ_NEXT();
            if (BPCRBPDT.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYPE_HAVE_SUB_TY;
                S000_ERR_MSG_PROC();
            }
        }
        B042_03_END_BROWSE();
    }
    public void B041_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCTPDME.INFO.INDEX_FLG = '2';
        if (BPCSMPDT.I_FUNC == 'A') {
            BPRPDME.BUSI_TYPE = WS_PRDT_TYPE;
        } else {
            BPRPDME.BUSI_TYPE = BPCSMPDT.PRDT_TYPE;
        }
        BPCTPDME.INFO.POINTER = BPRPDME;
        BPCTPDME.LEN = 516;
        S000_CALL_BPZTPDME();
    }
    public void B041_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'N';
        S000_CALL_BPZTPDME();
    }
    public void B041_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
    }
    public void B042_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRBPDT);
        BPCRBPDT.INFO.FUNC = 'S';
        BPCRBPDT.REQID = 2;
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRBPDT.INFO.POINTER = BPRPDTP;
        BPCRBPDT.INFO.LEN = 237;
        S000_CALL_BPZRBPDT();
    }
    public void B042_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRBPDT();
    }
    public void B042_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRBPDT();
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B042_CHECK_PDT_SUB();
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'Q';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
        WS_PRDT_TYPE = BPRPDTP.SUPR_TYPE;
        B041_CHECK_PDT_MENU();
        if (WS_PRDT_TYPE.trim().length() > 0) {
            B052_CHECK_SUPR_TYPE_SUB();
            if (WS_COUNT <= 1) {
                B051_MODIFY_SUPR_TYPE();
            }
        }
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'R';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
        WS_OUT_DATA.WS_OUT_PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
        WS_OUT_DATA.WS_OUT_BOTTOM_IND = BPRPDTP.BOTTOM_IND;
        WS_OUT_DATA.WS_OUT_SUPR_TYPE = BPRPDTP.SUPR_TYPE;
        WS_OUT_DATA.WS_OUT_DESC = BPRPDTP.DESC;
        WS_OUT_DATA.WS_OUT_DESC_ENG = BPRPDTP.DESC_ENG;
        WS_OUT_DATA.WS_OUT_OPEN_DATE = BPRPDTP.OPEN_DATE;
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'D';
        BPRPDTP.KEY.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
    }
    public void B051_MODIFY_SUPR_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'R';
        BPRPDTP.KEY.PRDT_TYPE = WS_PRDT_TYPE;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
        WS_SUPR_TYPE = BPRPDTP.SUPR_TYPE;
        WS_BOTTOM_IND = BPRPDTP.BOTTOM_IND;
        WS_DESC = BPRPDTP.DESC;
        WS_DESC_ENG = BPRPDTP.DESC_ENG;
        WS_OPEN_DATE = BPRPDTP.OPEN_DATE;
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'U';
        BPRPDTP.KEY.PRDT_TYPE = WS_PRDT_TYPE;
        if (BPCSMPDT.I_FUNC == 'A') {
            BPRPDTP.BOTTOM_IND = 'M';
        }
        if (BPCSMPDT.I_FUNC == 'D' 
            && WS_BOTTOM_IND != 'T') {
            BPRPDTP.BOTTOM_IND = 'B';
        }
        if (BPCSMPDT.I_FUNC == 'D' 
            && WS_BOTTOM_IND == 'T') {
            BPRPDTP.BOTTOM_IND = 'T';
        }
        BPRPDTP.SUPR_TYPE = WS_SUPR_TYPE;
        BPRPDTP.DESC = WS_DESC;
        BPRPDTP.DESC_ENG = WS_DESC_ENG;
        BPRPDTP.OPEN_DATE = WS_OPEN_DATE;
        BPRPDTP.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPDTP.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
    }
    public void B052_CHECK_SUPR_TYPE_SUB() throws IOException,SQLException,Exception {
        B052_01_START_BROWSE();
        while (BPCRBPDT.RETURN_INFO != 'N') {
            B042_02_READ_NEXT();
            if (BPCRBPDT.RETURN_INFO == 'F') {
                WS_COUNT += 1;
            }
        }
        B042_03_END_BROWSE();
    }
    public void B052_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRBPDT);
        BPCRBPDT.INFO.FUNC = 'S';
        BPCRBPDT.REQID = 2;
        BPRPDTP.KEY.PRDT_TYPE = WS_PRDT_TYPE;
        BPCRBPDT.INFO.POINTER = BPRPDTP;
        BPCRBPDT.INFO.LEN = 237;
        S000_CALL_BPZRBPDT();
    }
    public void B050_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDT.PRDT_TYPE;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPDTP;
        S000_CALL_BPZPNHIS();
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, BPCSMPDT.I_FUNC);
        if (BPCSMPDT.I_FUNC == 'D') {
            BPCSMPDT.PRDT_TYPE = WS_OUT_DATA.WS_OUT_PRDT_TYPE;
            BPCSMPDT.BOTTOM_IND = WS_OUT_DATA.WS_OUT_BOTTOM_IND;
            BPCSMPDT.SUPR_TYPE = WS_OUT_DATA.WS_OUT_SUPR_TYPE;
            BPCSMPDT.DESC = WS_OUT_DATA.WS_OUT_DESC;
            BPCSMPDT.DESC_ENG = WS_OUT_DATA.WS_OUT_DESC_ENG;
            WS_OPEN_DATE = WS_OUT_DATA.WS_OUT_OPEN_DATE;
        } else {
            BPCSMPDT.PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
            BPCSMPDT.BOTTOM_IND = BPRPDTP.BOTTOM_IND;
            BPCSMPDT.SUPR_TYPE = BPRPDTP.SUPR_TYPE;
            BPCSMPDT.DESC = BPRPDTP.DESC;
            BPCSMPDT.DESC_ENG = BPRPDTP.DESC_ENG;
            WS_OPEN_DATE = BPRPDTP.OPEN_DATE;
        }
        if (BPCSMPDT.SUPR_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPDT);
            BPCPQPDT.PRDT_TYPE = BPCSMPDT.SUPR_TYPE;
            S000_CALL_BPZPQPDT();
            if (BPCPQPDT.RC.RC_CODE == 0) {
                WS_SUPR_DESC = BPCPQPDT.DESC;
                WS_SUPR_DESC_ENG = BPCPQPDT.DESC_ENG;
            }
        }
        CEP.TRC(SCCGWA, BPCPQPDT.PRDT_TYPE);
        CEP.TRC(SCCGWA, BPCPQPDT.DESC);
        IBS.init(SCCGWA, BPCO702);
        IBS.init(SCCGWA, SCCFMT);
        BPCO702.PRDT_TYPE = BPCSMPDT.PRDT_TYPE;
        BPCO702.BOTTOM_IND = BPCSMPDT.BOTTOM_IND;
        BPCO702.SUPR_TYPE = BPCSMPDT.SUPR_TYPE;
        BPCO702.DESC = BPCSMPDT.DESC;
        BPCO702.DESC_ENG = BPCSMPDT.DESC_ENG;
        BPCO702.FLAG = 0X02;
        BPCO702.SUPR_DESC = WS_SUPR_DESC;
        BPCO702.SUPR_DESC_ENG = WS_SUPR_DESC_ENG;
        BPCO702.SUPR_FLAG = 0X02;
        SCCFMT.FMTID = "BP702";
        SCCFMT.DATA_PTR = BPCO702;
        SCCFMT.DATA_LEN = 369;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRMPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_MT, BPCRMPDT);
        if (BPCRMPDT.RETURN_INFO == 'F') {
        } else if (BPCRMPDT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
        } else if (BPCRMPDT.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYPE_EXIST;
            S000_ERR_MSG_PROC();
        } else {
        }
    }
    public void S000_CALL_BPZTPDME() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDME_BROWER, BPCTPDME);
    }
    public void S000_CALL_BPZRBPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_BROWER, BPCRBPDT);
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

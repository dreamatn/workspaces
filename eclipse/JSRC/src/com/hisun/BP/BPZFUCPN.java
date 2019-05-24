package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFUCPN {
    int JIBS_tmp_int;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String WS_ERR_MSG = " ";
    BPZFUCPN_WS_FEE_DESC[] WS_FEE_DESC = new BPZFUCPN_WS_FEE_DESC[20];
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_IDX = 0;
    short WS_NO = 0;
    String WS_FEE_INFO = " ";
    BPZFUCPN_WS_MSK_VAL WS_MSK_VAL = new BPZFUCPN_WS_MSK_VAL();
    char WS_INPUT_CHANGE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFCPN BPVFCPN = new BPVFCPN();
    BPVFCPN BPVHCPN = new BPVFCPN();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    BPCOFCPN BPCOUCPN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZFUCPN() {
        for (int i=0;i<20;i++) WS_FEE_DESC[i] = new BPZFUCPN_WS_FEE_DESC();
    }
    public void MP(SCCGWA SCCGWA, BPCOFCPN BPCOUCPN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUCPN = BPCOUCPN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFUCPN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFCPN);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUCPN.FUNC == 'I') {
            B010_QUERY_PROCESS();
        } else if (BPCOUCPN.FUNC == 'A') {
            B020_CREATE_PROCESS();
        } else if (BPCOUCPN.FUNC == 'U') {
            B030_MODIFY_PROCESS();
        } else if (BPCOUCPN.FUNC == 'D') {
            B040_DELETE_PROCESS();
        } else if (BPCOUCPN.FUNC == 'B') {
            B050_BROWSE_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCPN);
        BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        S000_CHECK_RETURN();
        R000_TRANS_DATE_OUTPUT();
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                && BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                R000_GET_FEE_NO();
            }
        }
        IBS.init(SCCGWA, BPVFCPN);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '0';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (BPCFPARM.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPVFMSK);
            BPVFMSK.KEY.KEY_TYPE = 'C';
            BPVFMSK.KEY.PRD_CODE = " ";
            BPVFMSK.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
            BPVFMSK.VAL.MATCH_FLG = ' ';
            BPVFMSK.VAL.FMT_NO = " ";
            BPVFMSK.VAL.EFF_DT = BPCOUCPN.VAL.EFF_DATE;
            BPVFMSK.VAL.EXP_DT = BPCOUCPN.VAL.EXP_DATE;
            for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
                if (BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                    && !BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                    && BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                    R000_GET_FEE_NO();
                    WS_FEE_DESC[WS_CNT1-1].FEE_DESC = BPRFBAS.FEE_DESC;
                    WS_IDX = (short) (BPRFBAS.FEE_NO / 254);
                    WS_NO = (short) (BPRFBAS.FEE_NO - WS_IDX * 254);
                    if (WS_IDX == 0) {
                        if (BPVFMSK.VAL.MASK_1 == null) BPVFMSK.VAL.MASK_1 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_1.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_1 += " ";
                        BPVFMSK.VAL.MASK_1 = BPVFMSK.VAL.MASK_1.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_1.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 1) {
                        if (BPVFMSK.VAL.MASK_2 == null) BPVFMSK.VAL.MASK_2 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_2.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_2 += " ";
                        BPVFMSK.VAL.MASK_2 = BPVFMSK.VAL.MASK_2.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_2.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 2) {
                        if (BPVFMSK.VAL.MASK_3 == null) BPVFMSK.VAL.MASK_3 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_3.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_3 += " ";
                        BPVFMSK.VAL.MASK_3 = BPVFMSK.VAL.MASK_3.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_3.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 3) {
                        if (BPVFMSK.VAL.MASK_4 == null) BPVFMSK.VAL.MASK_4 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_4.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_4 += " ";
                        BPVFMSK.VAL.MASK_4 = BPVFMSK.VAL.MASK_4.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_4.substring(WS_NO + 1 - 1);
                    } else {
                    }
                }
            }
            IBS.init(SCCGWA, BPCFPARM);
            BPCFPARM.INFO.POINTER = BPVFMSK;
            BPCFPARM.INFO.FUNC = '0';
            BPCFPARM.INFO.TYPE = "FMSK ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FMAK_RECORD_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
        R000_TRANS_DATE_OUTPUT();
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCPN);
        IBS.init(SCCGWA, BPCFPARM);
        BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND;
            S000_ERR_MSG_PROC();
        }
        R000_UPDATE_CHECK();
        IBS.CLONE(SCCGWA, BPVFCPN, BPVHCPN);
        B020_01_HISTORY_RECORD();
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                && BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                R000_GET_FEE_NO();
            }
        }
        IBS.init(SCCGWA, BPVFCPN);
        IBS.init(SCCGWA, BPCFPARM);
        BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '4';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND;
            S000_ERR_MSG_PROC();
        }
        R000_TRANS_DATA_PARAMETER();
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '1';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        CEP.TRC(SCCGWA, BPCFPARM.RC);
        CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        if (BPCFPARM.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPVFMSK);
            BPVFMSK.KEY.KEY_TYPE = 'C';
            BPVFMSK.KEY.PRD_CODE = " ";
            BPVFMSK.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
            BPVFMSK.VAL.MATCH_FLG = ' ';
            BPVFMSK.VAL.FMT_NO = " ";
            BPVFMSK.VAL.EFF_DT = BPVHCPN.VAL.EFF_DATE;
            BPVFMSK.VAL.EXP_DT = BPVHCPN.VAL.EXP_DATE;
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EFF_DT);
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EXP_DT);
            BPCFPARM.INFO.POINTER = BPVFMSK;
            BPCFPARM.INFO.FUNC = '4';
            BPCFPARM.INFO.TYPE = "FMSK ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FMAK_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPVFMSK.VAL);
            for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
                CEP.TRC(SCCGWA, BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE);
                if (BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                    && !BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                    && BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                    R000_GET_FEE_NO();
                    WS_FEE_DESC[WS_CNT1-1].FEE_DESC = BPRFBAS.FEE_DESC;
                    CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
                    WS_IDX = (short) (BPRFBAS.FEE_NO / 254);
                    WS_NO = (short) (BPRFBAS.FEE_NO - WS_IDX * 254);
                    if (WS_IDX == 0) {
                        if (BPVFMSK.VAL.MASK_1 == null) BPVFMSK.VAL.MASK_1 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_1.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_1 += " ";
                        BPVFMSK.VAL.MASK_1 = BPVFMSK.VAL.MASK_1.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_1.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 1) {
                        if (BPVFMSK.VAL.MASK_2 == null) BPVFMSK.VAL.MASK_2 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_2.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_2 += " ";
                        BPVFMSK.VAL.MASK_2 = BPVFMSK.VAL.MASK_2.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_2.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 2) {
                        if (BPVFMSK.VAL.MASK_3 == null) BPVFMSK.VAL.MASK_3 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_3.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_3 += " ";
                        BPVFMSK.VAL.MASK_3 = BPVFMSK.VAL.MASK_3.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_3.substring(WS_NO + 1 - 1);
                    } else if (WS_IDX == 3) {
                        if (BPVFMSK.VAL.MASK_4 == null) BPVFMSK.VAL.MASK_4 = "";
                        JIBS_tmp_int = BPVFMSK.VAL.MASK_4.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFMSK.VAL.MASK_4 += " ";
                        BPVFMSK.VAL.MASK_4 = BPVFMSK.VAL.MASK_4.substring(0, WS_NO - 1) + "1" + BPVFMSK.VAL.MASK_4.substring(WS_NO + 1 - 1);
                    } else {
                    }
                }
            }
            BPVFMSK.VAL.EFF_DT = BPVFCPN.VAL.EFF_DATE;
            BPVFMSK.VAL.EXP_DT = BPVFCPN.VAL.EXP_DATE;
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EFF_DT);
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EXP_DT);
            BPCFPARM.INFO.POINTER = BPVFMSK;
            BPCFPARM.INFO.FUNC = '1';
            BPCFPARM.INFO.TYPE = "FMSK ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FMAK_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        R000_TRANS_DATE_OUTPUT();
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCPN);
        BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '4';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'F') {
            R000_TRANS_DATE_OUTPUT();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.CLONE(SCCGWA, BPVFCPN, BPVHCPN);
        B020_01_HISTORY_RECORD();
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFCPN;
        BPCFPARM.INFO.FUNC = '2';
        BPCFPARM.INFO.TYPE = "FCPN ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPVFMSK);
            BPVFMSK.KEY.KEY_TYPE = 'C';
            BPVFMSK.KEY.PRD_CODE = " ";
            BPVFMSK.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
            BPVFMSK.VAL.EFF_DT = BPCOUCPN.VAL.EFF_DATE;
            BPVFMSK.VAL.EXP_DT = BPCOUCPN.VAL.EXP_DATE;
            BPCFPARM.INFO.POINTER = BPVFMSK;
            BPCFPARM.INFO.FUNC = '4';
            BPCFPARM.INFO.TYPE = "FMSK ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FMAK_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPCFPARM.INFO.POINTER = BPVFMSK;
            BPCFPARM.INFO.FUNC = '2';
            BPCFPARM.INFO.TYPE = "FMSK ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FMAK_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        S000_CHECK_RETURN();
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCPN);
        IBS.init(SCCGWA, BPCFPARM);
        if (BPCOUCPN.KEY.CPNT_ID.trim().length() > 0) {
            BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        }
        CEP.TRC(SCCGWA, "CPNT-ID");
        CEP.TRC(SCCGWA, BPVFCPN.KEY.CPNT_ID);
        BPCFPARM.INFO.FUNC = '5';
        BPCFPARM.INFO.POINTER = BPVFCPN;
        if (BPCOUCPN.OPT == 'S') {
            BPCFPARM.INFO.OPT = '0';
            BPCFPARM.INFO.TYPE = "FCPN ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCOUCPN.OPT == 'N') {
            BPCFPARM.INFO.OPT = '1';
            BPCFPARM.INFO.TYPE = "FCPN ";
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                BPCOUCPN.FOUND_FLG = 'N';
            } else {
                BPCOUCPN.FOUND_FLG = 'F';
                R000_TRANS_DATE_OUTPUT();
            }
        } else if (BPCOUCPN.OPT == 'E') {
            BPCFPARM.INFO.OPT = '2';
            BPCFPARM.INFO.TYPE = "FCPN ";
            S000_CALL_BPZFPARM();
        } else {
        }
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        BPCFPARM.INFO.POINTER_OLD = BPVHCPN;
    }
    public void R000_GET_FEE_NO_FORINQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
    }
    public void R000_GET_FEE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (BPCTFBAS.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            WS_FEE_INFO = BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE;
            S000_ERR_MSG_PROC_FEE_CODE();
        }
    }
    public void R000_UPDATE_CHECK() throws IOException,SQLException,Exception {
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (!BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.equalsIgnoreCase(BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE) 
                || BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].LNK_FLG != BPVFCPN.VAL.VAL1[WS_CNT1-1].LNK_FLG 
                || !BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].STR_CND.equalsIgnoreCase(BPVFCPN.VAL.VAL1[WS_CNT1-1].STR_CND)) {
                WS_INPUT_CHANGE = 'Y';
            }
        }
        if (BPCOUCPN.VAL.EFF_DATE != BPVFCPN.VAL.EFF_DATE 
            || BPCOUCPN.VAL.EXP_DATE != BPVFCPN.VAL.EXP_DATE) {
            WS_INPUT_CHANGE = 'Y';
        }
        if (WS_INPUT_CHANGE != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCPN);
        BPVFCPN.KEY.CPNT_ID = BPCOUCPN.KEY.CPNT_ID;
        BPVFCPN.VAL.EFF_DATE = BPCOUCPN.VAL.EFF_DATE;
        BPVFCPN.VAL.EXP_DATE = BPCOUCPN.VAL.EXP_DATE;
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE = BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE;
            BPVFCPN.VAL.VAL1[WS_CNT1-1].LNK_FLG = BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].LNK_FLG;
            BPVFCPN.VAL.VAL1[WS_CNT1-1].STR_CND = BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].STR_CND;
        }
        BPVFCPN.VAL.LAST_DATE = BPCOUCPN.VAL.LAST_DATE;
        BPVFCPN.VAL.LAST_TELL = BPCOUCPN.VAL.LAST_TELL;
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_PARM, BPCFPARM);
    }
    public void R000_TRANS_DATE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUCPN.KEY);
        IBS.init(SCCGWA, BPCOUCPN.VAL);
        BPCOUCPN.KEY.CPNT_ID = BPVFCPN.KEY.CPNT_ID;
        BPCOUCPN.VAL.EFF_DATE = BPVFCPN.VAL.EFF_DATE;
        BPCOUCPN.VAL.EXP_DATE = BPVFCPN.VAL.EXP_DATE;
        CEP.TRC(SCCGWA, BPCOUCPN.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOUCPN.VAL.EXP_DATE);
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                && BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                R000_GET_FEE_NO_FORINQ();
                BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE = BPVFCPN.VAL.VAL1[WS_CNT1-1].FEE_CODE;
                BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_DESC = BPRFBAS.FEE_DESC;
                CEP.TRC(SCCGWA, BPRFBAS.FEE_DESC);
                BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].LNK_FLG = BPVFCPN.VAL.VAL1[WS_CNT1-1].LNK_FLG;
                BPCOUCPN.VAL.CPN_DATE[WS_CNT1-1].STR_CND = BPVFCPN.VAL.VAL1[WS_CNT1-1].STR_CND;
            }
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_T_FEE_INFO, BPCTFBAS);
    }
    public void S000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCFPARM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (BPCFPARM.RETURN_INFO == 'N' 
            && BPCFPARM.INFO.FUNC == '5') {
            BPCOUCPN.FOUND_FLG = 'N';
        } else {
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC_FEE_CODE() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FEE_INFO);
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

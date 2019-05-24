package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3180 {
    int JIBS_tmp_int;
    String OUTPUT_FMT = "BP318";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String R_MGM_TBVD = "BP-R-MGM-TBVD";
    String F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String P_CKG_CLIB = "BP-P-CKG-CLIB";
    String REC_NHIS = "BP-REC-NHIS         ";
    String R_BRW_TBVD = "BP-R-BRW-TBVD";
    BPOT3180_WS_VARIABLES WS_VARIABLES = new BPOT3180_WS_VARIABLES();
    BPOT3180_WS_HIS_REMARKS WS_HIS_REMARKS = new BPOT3180_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCO318 BPCO318 = new BPCO318();
    BPCPCLIB BPCPCLIB = new BPCPCLIB();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPCRBUSB BPCRBUSB = new BPCRBUSB();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPB3180_AWA_3180 AWA_3180;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3180 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_3180 = new BPB3180_AWA_3180();
        IBS.init(SCCGWA, AWA_3180);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_3180);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_3180.TR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, AWA_3180.TR_DATE);
        CEP.TRC(SCCGWA, AWA_3180.TR_JRN);
        CEP.TRC(SCCGWA, AWA_3180.HEAD_NO);
        CEP.TRC(SCCGWA, AWA_3180.BEG_NO);
        CEP.TRC(SCCGWA, AWA_3180.END_NO);
        CEP.TRC(SCCGWA, AWA_3180.BV_CODE);
        AWA_3180.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (AWA_3180.BV_CODE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = AWA_3180.BV_CODE;
            S000_CALL_BPZFBVQU();
        }
        if (AWA_3180.BEG_NO.trim().length() > 0) {
            if (AWA_3180.BEG_NO == null) AWA_3180.BEG_NO = "";
            JIBS_tmp_int = AWA_3180.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.BEG_NO += " ";
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                && IBS.isNumeric(AWA_3180.BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
            }
            CEP.TRC(SCCGWA, "12341234");
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_VARIABLES.FLD_NO = AWA_3180.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (AWA_3180.END_NO.trim().length() > 0) {
            if (AWA_3180.END_NO == null) AWA_3180.END_NO = "";
            JIBS_tmp_int = AWA_3180.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.END_NO += " ";
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                && IBS.isNumeric(AWA_3180.END_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_VARIABLES.FLD_NO = AWA_3180.END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_VARIABLES.BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (AWA_3180.HEAD_NO.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_HEADNO;
                WS_VARIABLES.FLD_NO = AWA_3180.HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (AWA_3180.BEG_NO.trim().length() > 0 
                || AWA_3180.END_NO.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_BEGEND_NO;
                WS_VARIABLES.FLD_NO = AWA_3180.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (AWA_3180.BEG_NO == null) AWA_3180.BEG_NO = "";
            JIBS_tmp_int = AWA_3180.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.BEG_NO += " ";
            if (AWA_3180.BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_BEGNO = 0;
            else WS_VARIABLES.COMP_BEGNO = Long.parseLong(AWA_3180.BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN));
            if (AWA_3180.END_NO == null) AWA_3180.END_NO = "";
            JIBS_tmp_int = AWA_3180.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.END_NO += " ";
            if (AWA_3180.END_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_ENDNO = 0;
            else WS_VARIABLES.COMP_ENDNO = Long.parseLong(AWA_3180.END_NO.substring(0, WS_VARIABLES.BVNO_LEN));
            if (WS_VARIABLES.COMP_BEGNO > WS_VARIABLES.COMP_ENDNO) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END;
                WS_VARIABLES.FLD_NO = AWA_3180.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_VARIABLES.COMP_BEGNO == 0 
                || WS_VARIABLES.COMP_ENDNO == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END_NUM;
                WS_VARIABLES.FLD_NO = AWA_3180.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            WS_VARIABLES.NUM = (int) (WS_VARIABLES.COMP_ENDNO - WS_VARIABLES.COMP_BEGNO + 1);
            CEP.TRC(SCCGWA, WS_VARIABLES.NUM);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        BPRBUSE.KEY.BR = AWA_3180.TR_BR;
        BPRBUSE.KEY.BV_CODE = AWA_3180.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = AWA_3180.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = AWA_3180.BEG_NO;
        BPRBUSE.KEY.END_NO = AWA_3180.END_NO;
        BPRBUSE.KEY.TX_DT = AWA_3180.TR_DATE;
        BPRBUSE.KEY.TX_JRN = AWA_3180.TR_JRN;
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        BPCRBUSE.INFO.FUNC = 'Q';
        S000_CALL_BPZRBUSE();
        if (BPCRBUSE.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BUSE_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRBUSE.STS != '3') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BV_STSERR;
            S000_ERR_MSG_PROC();
        }
        B021_CHK_BV_TLR();
        B022_ADD_TBVD();
        B023_ADD_BUSE();
        B024_UPDATE_BPTVHPB();
        B025_REC_NHIS();
        B026_DATA_OUTPUT();
    }
    public void B021_CHK_BV_TLR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBUSE.TX_TLR);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPRBUSE.TX_TLR;
        S000_CALL_BPZFTLRQ();
        IBS.init(SCCGWA, BPCPCLIB);
        BPCPCLIB.TLR = BPRBUSE.TX_TLR;
        BPCPCLIB.VB_FLG = '1';
        S000_CALL_BPZPCLIB();
        if (BPCFBVQU.TX_DATA.TYPE == '0'
            || BPCFBVQU.TX_DATA.TYPE == '2'
            || BPCFBVQU.TX_DATA.TYPE == '3'
            || BPCFBVQU.TX_DATA.TYPE == '4') {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = AWA_3180.TR_BR;
            BPRVHPB.CUR_TLR = BPRBUSE.TX_TLR;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_VARIABLES.TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_VARIABLES.TEMP_PLBOX_NO);
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_BVV_TLR;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'R';
            S000_CALL_BPZRVHPB();
        } else if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCPCLIB.FLG == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_CASH_VLT;
                S000_ERR_MSG_PROC();
            }
        } else {
        }
    }
    public void B022_ADD_TBVD() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = AWA_3180.TR_BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = AWA_3180.BV_CODE;
            BPRTBVD.KEY.STS = '1';
            BPRTBVD.KEY.VALUE = 0;
            BPRTBVD.VIL_TYP = " ";
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                BPRTBVD.TYPE = BPCFBVQU.TX_DATA.TYPE;
                BPRTBVD.NUM = WS_VARIABLES.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'A';
                S000_CALL_BPZRTBVD();
            } else {
                BPRTBVD.NUM = BPRTBVD.NUM + WS_VARIABLES.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'U';
                S000_CALL_BPZRTBVD();
            }
        } else if (BPCFBVQU.TX_DATA.CTL_FLG == '1'
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            IBS.init(SCCGWA, BPRTBVD);
            WS_VARIABLES.POS = 20;
            CEP.TRC(SCCGWA, WS_VARIABLES.POS);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            WS_VARIABLES.POS = WS_VARIABLES.POS - BPCFBVQU.TX_DATA.NO_LENGTH + 1;
            if (AWA_3180.BEG_NO == null) AWA_3180.BEG_NO = "";
            JIBS_tmp_int = AWA_3180.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.BEG_NO += " ";
            if (AWA_3180.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_VARIABLES.TMP_BEGNO = 0;
            else WS_VARIABLES.TMP_BEGNO = Long.parseLong(AWA_3180.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            if (AWA_3180.END_NO == null) AWA_3180.END_NO = "";
            JIBS_tmp_int = AWA_3180.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3180.END_NO += " ";
            if (AWA_3180.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_VARIABLES.TMP_ENDNO = 0;
            else WS_VARIABLES.TMP_ENDNO = Long.parseLong(AWA_3180.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            BPRTBVD.KEY.STS = '1';
            R_FIND_RESEMBLE_REC();
            BPRTBVD.KEY.BR = AWA_3180.TR_BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = AWA_3180.BV_CODE;
            BPRTBVD.KEY.VALUE = 0;
            BPRTBVD.KEY.HEAD_NO = AWA_3180.HEAD_NO;
            BPRTBVD.TYPE = BPCFBVQU.TX_DATA.TYPE;
            BPRTBVD.NOW_NO = " ";
            BPRTBVD.VIL_TYP = " ";
            if (WS_VARIABLES.FORWARD_FLG == 'Y'
                && WS_VARIABLES.BACKWARD_FLG == 'Y') {
                BPRTBVD.KEY.END_NO = WS_VARIABLES.TMP_ENDNO_X;
                BPRTBVD.BEG_NO = WS_VARIABLES.TMP_BEGNO_X;
                CEP.TRC(SCCGWA, "1");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (WS_VARIABLES.FORWARD_FLG == 'Y'
                && WS_VARIABLES.BACKWARD_FLG == 'N') {
                BPRTBVD.KEY.END_NO = WS_VARIABLES.TMP_ENDNO_X;
                BPRTBVD.BEG_NO = AWA_3180.BEG_NO;
                CEP.TRC(SCCGWA, "2");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (WS_VARIABLES.FORWARD_FLG == 'N'
                && WS_VARIABLES.BACKWARD_FLG == 'Y') {
                BPRTBVD.BEG_NO = WS_VARIABLES.TMP_BEGNO_X;
                BPRTBVD.KEY.END_NO = AWA_3180.END_NO;
                CEP.TRC(SCCGWA, "3");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (WS_VARIABLES.FORWARD_FLG == 'N'
                && WS_VARIABLES.BACKWARD_FLG == 'N') {
                BPRTBVD.BEG_NO = AWA_3180.BEG_NO;
                BPRTBVD.KEY.END_NO = AWA_3180.END_NO;
                CEP.TRC(SCCGWA, "4");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else {
            }
            if (BPRTBVD.BEG_NO == null) BPRTBVD.BEG_NO = "";
            JIBS_tmp_int = BPRTBVD.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.BEG_NO += " ";
            if (BPRTBVD.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_VARIABLES.TMP_BEGNO = 0;
            else WS_VARIABLES.TMP_BEGNO = Long.parseLong(BPRTBVD.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            if (BPRTBVD.KEY.END_NO == null) BPRTBVD.KEY.END_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.KEY.END_NO += " ";
            if (BPRTBVD.KEY.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_VARIABLES.TMP_ENDNO = 0;
            else WS_VARIABLES.TMP_ENDNO = Long.parseLong(BPRTBVD.KEY.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            BPRTBVD.NUM = (int) (WS_VARIABLES.TMP_ENDNO - WS_VARIABLES.TMP_BEGNO + 1);
            BPRTBVD.KEY.STS = '1';
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NTOT_DUPLICATE;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B023_ADD_BUSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = AWA_3180.TR_BR;
        BPRBUSE.KEY.BV_CODE = AWA_3180.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = AWA_3180.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = AWA_3180.BEG_NO;
        BPRBUSE.KEY.END_NO = AWA_3180.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = AWA_3180.TR_BR;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '1';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B024_UPDATE_BPTVHPB() throws IOException,SQLException,Exception {
        BPRVHPB.BV_CHK_FLG = 'N';
        BPRVHPB.BL_CHK_FLG = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
    }
    public void B025_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_BV_CODE = AWA_3180.BV_CODE;
        WS_HIS_REMARKS.HIS_HEAD_NO = AWA_3180.HEAD_NO;
        WS_HIS_REMARKS.HIS_BEG_NO = AWA_3180.BEG_NO;
        WS_HIS_REMARKS.HIS_END_NO = AWA_3180.END_NO;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B026_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO318);
        BPCO318.BV_CODE = AWA_3180.BV_CODE;
        BPCO318.HEAD_NO = AWA_3180.HEAD_NO;
        BPCO318.BEG_NO = AWA_3180.BEG_NO;
        BPCO318.END_NO = AWA_3180.END_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO318;
        SCCFMT.DATA_LEN = 56;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R_FIND_RESEMBLE_REC() throws IOException,SQLException,Exception {
        BPRTBVD.KEY.BR = AWA_3180.TR_BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = AWA_3180.BV_CODE;
        BPRTBVD.KEY.VALUE = 0;
        BPRTBVD.KEY.HEAD_NO = AWA_3180.HEAD_NO;
        WS_VARIABLES.TMP_BEGNO -= 1;
        WS_VARIABLES.STORAGE = "" + WS_VARIABLES.TMP_BEGNO;
        JIBS_tmp_int = WS_VARIABLES.STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.STORAGE = "0" + WS_VARIABLES.STORAGE;
        CEP.TRC(SCCGWA, WS_VARIABLES.STORAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.POS);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        if (WS_VARIABLES.STORAGE == null) WS_VARIABLES.STORAGE = "";
        JIBS_tmp_int = WS_VARIABLES.STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.STORAGE += " ";
        BPRTBVD.KEY.END_NO = WS_VARIABLES.STORAGE.substring(WS_VARIABLES.POS - 1, WS_VARIABLES.POS + BPCFBVQU.TX_DATA.NO_LENGTH - 1);
        BPRTBVD.KEY.STS = '1';
        IBS.init(SCCGWA, BPCRTBVD);
        BPCRTBVD.INFO.FUNC = 'R';
        S000_CALL_BPZRTBVD();
        if (BPCRTBVD.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP1");
            WS_VARIABLES.BACKWARD_FLG = 'N';
        } else {
            WS_VARIABLES.BACKWARD_FLG = 'Y';
            WS_VARIABLES.TMP_BEGNO_X = BPRTBVD.BEG_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.TMP_BEGNO_X);
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'D';
            S000_CALL_BPZRTBVD();
        }
        CEP.TRC(SCCGWA, "STEP2");
        BPRTBVD.KEY.BR = AWA_3180.TR_BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = AWA_3180.BV_CODE;
        BPRTBVD.KEY.VALUE = 0;
        BPRTBVD.KEY.HEAD_NO = AWA_3180.HEAD_NO;
        WS_VARIABLES.TMP_ENDNO += 1;
        WS_VARIABLES.STORAGE = "" + WS_VARIABLES.TMP_ENDNO;
        JIBS_tmp_int = WS_VARIABLES.STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.STORAGE = "0" + WS_VARIABLES.STORAGE;
        if (WS_VARIABLES.STORAGE == null) WS_VARIABLES.STORAGE = "";
        JIBS_tmp_int = WS_VARIABLES.STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.STORAGE += " ";
        BPRTBVD.BEG_NO = WS_VARIABLES.STORAGE.substring(WS_VARIABLES.POS - 1, WS_VARIABLES.POS + BPCFBVQU.TX_DATA.NO_LENGTH - 1);
        BPRTBVD.KEY.STS = '1';
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '4';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP3");
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP4");
            WS_VARIABLES.FORWARD_FLG = 'N';
        } else {
            WS_VARIABLES.FORWARD_FLG = 'Y';
            WS_VARIABLES.TMP_ENDNO_X = BPRTBVD.KEY.END_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.TMP_ENDNO_X);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            S000_CALL_BPZRTBDB();
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP5");
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_CKG_CLIB, BPCPCLIB);
        if (BPCPCLIB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCLIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

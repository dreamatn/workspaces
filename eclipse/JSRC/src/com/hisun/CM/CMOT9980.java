package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;

public class CMOT9980 {
    boolean pgmRtn = false;
    int RESP = 0;
    String OUTPUT_DATA = "                                                                                ";
    CMOT9980_WS_PARM WS_PARM = new CMOT9980_WS_PARM();
    CMOT9980_WS_RTN_FIL WS_RTN_FIL = new CMOT9980_WS_RTN_FIL();
    CMOT9980_WS_RTN_FIL2 WS_RTN_FIL2 = new CMOT9980_WS_RTN_FIL2();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CMRBSPM CMRBSPM = new CMRBSPM();
    BPCFITYP BPCFITYP = new BPCFITYP();
    SCCPRMR SCCPRMR = new SCCPRMR();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCRWBTL SCCRWBTL = new SCCRWBTL();
    CMCO9980 CMCO9980 = new CMCO9980();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB9980_AWA_9980 CMB9980_AWA_9980;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT9980 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB9980_AWA_9980>");
        CMB9980_AWA_9980 = (CMB9980_AWA_9980) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GEN_PARM_INF();
        if (pgmRtn) return;
        B030_READ_FITYP_INF();
        if (pgmRtn) return;
        B040_WRITE_BPTBBLT_INF();
        if (pgmRtn) return;
        B050_SUB_SPOOL_PROC();
        if (pgmRtn) return;
        B060_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GEN_PARM_INF() throws IOException,SQLException,Exception {
        WS_PARM.WS_PARM_DATA1.WS_REQ_SYSTEM = CMB9980_AWA_9980.REQ_SYS;
        WS_RTN_FIL2.WS_REQ_SYS = CMB9980_AWA_9980.REQ_SYS;
        WS_PARM.WS_PARM_DATA1.WS_TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_RTN_FIL.WS_RTN_BUS_TYP = CMB9980_AWA_9980.BUS_TYP;
        WS_RTN_FIL2.WS_RTN_BUS_TYP2 = CMB9980_AWA_9980.BUS_TYP;
        WS_PARM.WS_PARM_DATA3.WS_BUS_TYPE = CMB9980_AWA_9980.BUS_TYP;
        WS_RTN_FIL.WS_RTN_BUS_DATE = CMB9980_AWA_9980.BUS_DATE;
        WS_RTN_FIL2.WS_RTN_BUS_DATE2 = CMB9980_AWA_9980.BUS_DATE;
        JIBS_tmp_str[0] = "" + CMB9980_AWA_9980.BUS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_RTN_FIL.WS_RTN_BAT_NO == null) WS_RTN_FIL.WS_RTN_BAT_NO = "";
        JIBS_tmp_int = WS_RTN_FIL.WS_RTN_BAT_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_RTN_FIL.WS_RTN_BAT_NO += " ";
        WS_RTN_FIL.WS_RTN_BAT_NO = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1) + WS_RTN_FIL.WS_RTN_BAT_NO.substring(6);
        if (WS_RTN_FIL.WS_RTN_BAT_NO == null) WS_RTN_FIL.WS_RTN_BAT_NO = "";
        JIBS_tmp_int = WS_RTN_FIL.WS_RTN_BAT_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_RTN_FIL.WS_RTN_BAT_NO += " ";
        JIBS_tmp_str[0] = "" + CMB9980_AWA_9980.BAT_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_RTN_FIL.WS_RTN_BAT_NO = WS_RTN_FIL.WS_RTN_BAT_NO.substring(0, 7 - 1) + JIBS_tmp_str[0] + WS_RTN_FIL.WS_RTN_BAT_NO.substring(7 + 6 - 1);
        WS_RTN_FIL2.WS_RTN_BAT_NO2 = WS_RTN_FIL.WS_RTN_BAT_NO;
        WS_PARM.WS_PARM_DATA2.WS_TRO_INF = IBS.CLS2CPY(SCCGWA, WS_RTN_FIL2);
        WS_PARM.WS_PARM_DATA3.WS_SERV_CODE = "0359980";
        WS_PARM.WS_PARM_DATA3.WS_BAT_NO = WS_RTN_FIL.WS_RTN_BAT_NO;
        WS_PARM.WS_PARM_DATA3.WS_TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        WS_PARM.WS_PARM_DATA3.WS_REQ_SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = CMB9980_AWA_9980.BUS_TYP;
        if (WS_RTN_FIL.WS_RTN_BAT_NO.trim().length() == 0) CMRBSPM.KEY.AP_BATNO = 0;
        else CMRBSPM.KEY.AP_BATNO = Long.parseLong(WS_RTN_FIL.WS_RTN_BAT_NO);
        CMRBSPM.IN_DATA = CMB9980_AWA_9980.BAT_CTL;
        T000_WRITE_CMTBSPM();
        if (pgmRtn) return;
    }
    public void B030_READ_FITYP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFITYP);
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.FUNC = ' ';
        BPCFITYP.KEY.TYP = "FITYP";
        BPCFITYP.KEY.CD = CMB9980_AWA_9980.BUS_TYP;
        S000_CALL_SCZPRMR();
        if (pgmRtn) return;
        if (BPCFITYP.DATA_TXT.GEN_PROC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FITYP_PARM_ERR);
        }
        WS_PARM.WS_PROC.WS_PROC_NAME = BPCFITYP.DATA_TXT.GEN_PROC;
    }
    public void B040_WRITE_BPTBBLT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRBBTL);
        IBS.init(SCCGWA, SCCRWBTL);
        SCRBBTL.KEY.SERV_CODE = "0359980";
        SCRBBTL.KEY.AP_TYPE = WS_PARM.WS_PARM_DATA3.WS_BUS_TYPE;
        if (WS_PARM.WS_PARM_DATA3.WS_BAT_NO.trim().length() == 0) SCRBBTL.KEY.AP_BATNO = 0;
        else SCRBBTL.KEY.AP_BATNO = Long.parseLong(WS_PARM.WS_PARM_DATA3.WS_BAT_NO);
        SCRBBTL.RCV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCRBBTL.SUB_TLT = SCCGWA.COMM_AREA.TL_ID;
        SCRBBTL.PARM_DA1 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA1);
        SCRBBTL.PARM_DA2 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA2);
        SCRBBTL.PARM_DA3 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA3);
        SCRBBTL.PARM_DA4 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA4);
        SCRBBTL.RTN_FILE = IBS.CLS2CPY(SCCGWA, WS_RTN_FIL);
        SCRBBTL.STEP_NUM = 1;
        SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_PROC_NAME = WS_PARM.WS_PROC.WS_PROC_NAME;
        SCRBBTL.REDEFINES28.STEP_CTL_DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30);
        SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS = 'P';
        SCRBBTL.REDEFINES28.STEP_CTL_DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30);
        SCRBBTL.STEP_CTL_DATA = IBS.CLS2CPY(SCCGWA, SCRBBTL.REDEFINES28);
        SCCRWBTL.FUNC = 'C';
        SCCRWBTL.PTR = SCRBBTL;
        SCCRWBTL.LEN = 837;
        S000_CALL_SCZRWBTL();
        if (pgmRtn) return;
    }
    public void B050_SUB_SPOOL_PROC() throws IOException,SQLException,Exception {
        S000_OPEN_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "SET(" + IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA1) + ")";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "SET(" + IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA2) + ")";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "SET(" + IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA3) + ")";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "SET(" + IBS.CLS2CPY(SCCGWA, WS_PARM.WS_PARM_DATA4) + ")";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = WS_PARM.WS_PROC.WS_PROC_NAME + "()";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "IF RC = 0 THEN ";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   EXEC";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   PGM(SCBBSPS)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   TYPE(BAT)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   PARM(U&SV.&BT.&BN.1F)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   END-EXEC";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "ELSE";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   EXEC";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   PGM(SCBBSPS)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   TYPE(BAT)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   PARM(U&SV.&BT.&BN.1E)";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "   END-EXEC";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        OUTPUT_DATA = " ";
        OUTPUT_DATA = "END-IF";
        CEP.TRC(SCCGWA, OUTPUT_DATA);
        S000_WRITE_SPOOL();
        if (pgmRtn) return;
        S000_CLOSE_SPOOL();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO9980);
        CMCO9980.BUS_TYP = CMB9980_AWA_9980.BUS_TYP;
        CMCO9980.BUS_DATE = CMB9980_AWA_9980.BUS_DATE;
        CMCO9980.BAT_SEQ = CMB9980_AWA_9980.BAT_SEQ;
        CMCO9980.RTN_FILE = IBS.CLS2CPY(SCCGWA, WS_RTN_FIL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM998";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCO9980;
        SCCFMT.DATA_LEN = 80;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZPRMR() throws IOException,SQLException,Exception {
        SCCPRMR.DAT_PTR = BPCFITYP;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ", SCCPRMR);
        CEP.TRC(SCCGWA, SCCPRMR.RC);
        if (SCCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZRWBTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-MAINTAIN-BBTL", SCCRWBTL);
        CEP.TRC(SCCGWA, SCCRWBTL.RC);
        if (SCCRWBTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWBTL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_OPEN_SPOOL() throws IOException,SQLException,Exception {

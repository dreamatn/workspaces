package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5612 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CDC_M_COMPA_DR_PLAN = "DC-M-COMPA-DR-PLAN";
    String WS_MSG_ID = " ";
    double WS_TRIG_AMT = 0;
    DCOT5612_REDEFINES3 REDEFINES3 = new DCOT5612_REDEFINES3();
    String WS_PROD_CDE = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUMCID DCCUMCID = new DCCUMCID();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    DCB5612_AWA_5612 DCB5612_AWA_5612;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5612 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5612_AWA_5612>");
        DCB5612_AWA_5612 = (DCB5612_AWA_5612) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.AC_NO);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.CCY);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.CCY_TYP);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.PROCS_DT);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.PROCL_DT);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.LNK_AC);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.MRM_AMT);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.POST_OPT);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.DEP_RATE);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.OD_RATE);
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.SMR);
        if (DCB5612_AWA_5612.AC_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_MISSING;
            S000_ERR_MSG_PROC();
        }
        if (DCB5612_AWA_5612.MRM_AMT < 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_KEEP_AMT_INV;
            S000_ERR_MSG_PROC();
        }
        if (DCB5612_AWA_5612.PROD_CDE.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        if (DCB5612_AWA_5612.CCY.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        if (DCB5612_AWA_5612.PROCS_DT == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        if (DCB5612_AWA_5612.PROCL_DT == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        WS_PROD_CDE = DCB5612_AWA_5612.PROD_CDE;
        CEP.TRC(SCCGWA, DCB5612_AWA_5612.PROD_CDE);
        if (DCB5612_AWA_5612.LNK_AC.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_MST_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMCID);
        DCCUMCID.IO_AREA.FUNC_M = 'A';
        DCCUMCID.IO_AREA.AC_NO = DCB5612_AWA_5612.AC_NO;
        DCCUMCID.IO_AREA.PROD_CDE = WS_PROD_CDE;
        DCCUMCID.IO_AREA.PROCS_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUMCID.IO_AREA.PROCL_DT = DCB5612_AWA_5612.PROCL_DT;
        DCCUMCID.IO_AREA.MRM_AMT = DCB5612_AWA_5612.MRM_AMT;
        DCCUMCID.IO_AREA.CCY = DCB5612_AWA_5612.CCY;
        DCCUMCID.IO_AREA.CCY_TYP = DCB5612_AWA_5612.CCY_TYP;
        DCCUMCID.IO_AREA.LNK_AC = DCB5612_AWA_5612.LNK_AC;
        DCCUMCID.IO_AREA.POST_OPT = DCB5612_AWA_5612.POST_OPT;
        DCCUMCID.IO_AREA.DEP_RATE = DCB5612_AWA_5612.DEP_RATE;
        DCCUMCID.IO_AREA.OD_RATE = DCB5612_AWA_5612.OD_RATE;
        DCCUMCID.IO_AREA.SMR = DCB5612_AWA_5612.SMR;
        S000_CALL_DCZUMCID();
    }
    public void S000_CALL_DCZUMCID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDC_M_COMPA_DR_PLAN, DCCUMCID);
        if (DCCUMCID.O_AREA.MSG_ID.RC == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUMCID.O_AREA.MSG_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_MSG_ID == null) WS_MSG_ID = "";
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSG_ID += " ";
            WS_MSG_ID = "SC" + WS_MSG_ID.substring(2);
            if (WS_MSG_ID == null) WS_MSG_ID = "";
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSG_ID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSG_ID = WS_MSG_ID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSG_ID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

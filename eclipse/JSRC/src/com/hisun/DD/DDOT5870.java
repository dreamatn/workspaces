package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5870 {
    String CDD_M_VIRTUAL_AC = "DD-M-VIRTUAL-AC";
    String WS_MSG_ID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUMDVS DDCUMDVS = new DDCUMDVS();
    SCCGWA SCCGWA;
    DDB5870_AWA_5870 DDB5870_AWA_5870;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5870 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5870_AWA_5870>");
        DDB5870_AWA_5870 = (DDB5870_AWA_5870) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5870_AWA_5870.VS_AC);
        if (DDB5870_AWA_5870.VS_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_MST_IPT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5870_AWA_5870.VS_AC_NO);
        }
        if (DDB5870_AWA_5870.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5870_AWA_5870.CCY_NO);
        }
        CEP.TRC(SCCGWA, "CC");
        if (DDB5870_AWA_5870.CCY_TYP == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5870_AWA_5870.CCY_TYP_NO);
        }
        CEP.TRC(SCCGWA, DDB5870_AWA_5870.VS_AC_NM);
        if (DDB5870_AWA_5870.VS_AC_NM.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VACN_MST_IPT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5870_AWA_5870.VS_AC_NM_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUMDVS);
        DDCUMDVS.IO_AREA.VS_AC = DDB5870_AWA_5870.VS_AC;
        DDCUMDVS.IO_AREA.CCY = DDB5870_AWA_5870.CCY;
        DDCUMDVS.IO_AREA.CCY_TYP = DDB5870_AWA_5870.CCY_TYP;
        DDCUMDVS.IO_AREA.VS_AC_NM = DDB5870_AWA_5870.VS_AC_NM;
        DDCUMDVS.IO_AREA.VS_CN_NM = DDB5870_AWA_5870.VS_CN_NM;
        DDCUMDVS.IO_AREA.VS_CN_TL = DDB5870_AWA_5870.VS_CN_TL;
        DDCUMDVS.IO_AREA.VS_CN_AR = DDB5870_AWA_5870.VS_CN_AR;
        DDCUMDVS.IO_AREA.REMARK = DDB5870_AWA_5870.REMARK;
        DDCUMDVS.IO_AREA.VS_IDTYP = DDB5870_AWA_5870.VS_IDTYP;
        DDCUMDVS.IO_AREA.VS_IDNO = DDB5870_AWA_5870.VS_IDNO;
        DDCUMDVS.IO_AREA.VS_SYSNO = DDB5870_AWA_5870.VS_SYSNO;
        DDCUMDVS.IO_AREA.VS_FLG = DDB5870_AWA_5870.VS_FLG;
        DDCUMDVS.IO_AREA.CHNLNO = DDB5870_AWA_5870.CHNLNO;
        DDCUMDVS.IO_AREA.VS_INTAC = DDB5870_AWA_5870.VS_INTAC;
        DDCUMDVS.IO_AREA.VS_MMO = DDB5870_AWA_5870.VS_MMO;
        CEP.TRC(SCCGWA, DDB5870_AWA_5870.VS_MMO);
        S000_CALL_DDZUMDVS();
    }
    public void S000_CALL_DDZUMDVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_M_VIRTUAL_AC, DDCUMDVS);
        CEP.TRC(SCCGWA, DDCUMDVS.O_AREA.RC_CODE);
        CEP.TRC(SCCGWA, DDCUMDVS.O_AREA.MSG_ID);
        if (DDCUMDVS.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DDCUMDVS.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

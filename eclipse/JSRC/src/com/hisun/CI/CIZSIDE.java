package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSIDE {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    short WS_I = 0;
    CIZSIDE_WS_STS_INFO WS_STS_INFO = new CIZSIDE_WS_STS_INFO();
    String WS_BAS_IDE_STS = " ";
    short WS_BAS_IDE_STSCD = 0;
    short WS_STSCD = 0;
    int WS_MIN_DT = 0;
    int WS_DATE = 0;
    CIZSIDE_WS_MSGID WS_MSGID = new CIZSIDE_WS_MSGID();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICOSIDE CICOSIDE = new CICOSIDE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSIDE CICSIDE;
    public void MP(SCCGWA SCCGWA, CICSIDE CICSIDE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSIDE = CICSIDE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSIDE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (CICSIDE.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSIDE.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICSIDE.FUNC == 'D') {
            B040_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSIDE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSIDE.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 30; WS_I += 1) {
            B020_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSIDE.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        WS_BAS_IDE_STS = CIRBAS.IDE_STSW;
        if (CICSIDE.DATA.IDE_STS_CODE.trim().length() == 0) WS_STSCD = 0;
        else WS_STSCD = Short.parseShort(CICSIDE.DATA.IDE_STS_CODE);
        CEP.TRC(SCCGWA, WS_STSCD);
        if (WS_BAS_IDE_STS == null) WS_BAS_IDE_STS = "";
        JIBS_tmp_int = WS_BAS_IDE_STS.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) WS_BAS_IDE_STS += " ";
        if (WS_BAS_IDE_STS.substring(WS_STSCD - 1, WS_STSCD + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IDE_STS_CD_EXIST, CICSIDE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBASN);
        IBS.init(SCCGWA, CIRBASO);
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, WS_STSCD - 1) + "1" + CIRBAS.IDE_STSW.substring(WS_STSCD + 1 - 1);
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSIDE.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        WS_BAS_IDE_STS = CIRBAS.IDE_STSW;
        if (CICSIDE.DATA.IDE_STS_CODE.trim().length() == 0) WS_STSCD = 0;
        else WS_STSCD = Short.parseShort(CICSIDE.DATA.IDE_STS_CODE);
        CEP.TRC(SCCGWA, WS_STSCD);
        if (WS_BAS_IDE_STS == null) WS_BAS_IDE_STS = "";
        JIBS_tmp_int = WS_BAS_IDE_STS.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) WS_BAS_IDE_STS += " ";
        if (WS_BAS_IDE_STS.substring(WS_STSCD - 1, WS_STSCD + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IDE_STS_NOT_EXIST, CICSIDE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBASN);
        IBS.init(SCCGWA, CIRBASO);
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, WS_STSCD - 1) + "0" + CIRBAS.IDE_STSW.substring(WS_STSCD + 1 - 1);
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        if (CIRBAS.IDE_STSW.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
            R000_DATA_TRANS_TO_MPAG();
            if (pgmRtn) return;
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOSIDE);
            SCCMPAG.DATA_LEN = 14;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_DATA_TRANS_TO_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSIDE);
        CEP.TRC(SCCGWA, WS_I);
        CICOSIDE.DATA.CI_NOL = CIRBAS.KEY.CI_NO;
        CICOSIDE.DATA.STS_CODEL = "" + WS_I;
        JIBS_tmp_int = CICOSIDE.DATA.STS_CODEL.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) CICOSIDE.DATA.STS_CODEL = "0" + CICOSIDE.DATA.STS_CODEL;
        CEP.TRC(SCCGWA, CICOSIDE.DATA.STS_CODEL);
        CEP.TRC(SCCGWA, "OSIDE-DATA");
        CEP.TRC(SCCGWA, CICOSIDE.DATA);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, CICSIDE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, CICSIDE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICSIDE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICSIDE=");
            CEP.TRC(SCCGWA, CICSIDE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

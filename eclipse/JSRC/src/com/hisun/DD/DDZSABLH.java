package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSABLH {
    brParm DDTBALH_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_STR_DATE = 0;
    int WS_AC_DATE = 0;
    int WS_AC_DATE2 = 0;
    double WS_AC_BAL = 0;
    double WS_AC_BAL2 = 0;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    int WS_I = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACAC CICQACAC = new CICQACAC();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCANHIS BPCANHIS = new BPCANHIS();
    DDRCCY DDRCCY = new DDRCCY();
    DDRBALH DDRBALH = new DDRBALH();
    SCCGWA SCCGWA;
    DDCSABLH DDCSABLH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCSABLH DDCSABLH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSABLH = DDCSABLH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSABLH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_PLAN_INQ();
        if (pgmRtn) return;
        B300_PLAN_BRO();
        if (pgmRtn) return;
        if (WS_DATE2 > WS_DATE1) {
            B400_PLAN_BALH();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSABLH.AC);
        if (DDCSABLH.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_ACAC_M_INPUT, DDCSABLH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_PLAN_INQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.MTHS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DATE1 = SCCCLDT.DATE2;
    }
    public void B300_PLAN_BRO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBALH);
        CEP.TRC(SCCGWA, DDCSABLH.AC);
        DDRBALH.KEY.AC = DDCSABLH.AC;
        T000_STARTBR_DDTBALH();
        if (pgmRtn) return;
        WS_DATE2 = DDRBALH.KEY.STR_DATE;
        CEP.TRC(SCCGWA, WS_DATE2);
    }
    public void B400_PLAN_BALH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCANHIS);
        BPCANHIS.ACO_IAC = DDCSABLH.AC;
        BPCANHIS.STR_DT = WS_DATE1;
        BPCANHIS.END_DT = WS_DATE2;
        S000_CALL_BPZANHIS();
        if (pgmRtn) return;
        WS_STR_DATE = BPCANHIS.DT_INFO[1-1].AC_DT;
        WS_AC_BAL = BPCANHIS.DT_INFO[1-1].CURR_BAL;
        for (WS_I = 1; BPCANHIS.DT_INFO[WS_I-1].AC_DT != 0; WS_I += 1) {
            if (BPCANHIS.DT_INFO[WS_I-1].CURR_BAL != WS_AC_BAL) {
                WS_AC_DATE2 = BPCANHIS.DT_INFO[WS_I-1].AC_DT;
                WS_AC_BAL2 = BPCANHIS.DT_INFO[WS_I-1].CURR_BAL;
                S000_ADD_DDTBALH();
                if (pgmRtn) return;
                WS_STR_DATE = WS_AC_DATE2;
                WS_AC_BAL = WS_AC_BAL2;
            }
        }
    }
    public void S000_ADD_DDTBALH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBALH);
        DDRBALH.KEY.AC = DDCSABLH.AC;
        DDRBALH.KEY.STR_DATE = WS_STR_DATE;
        DDRBALH.END_DATE = WS_AC_DATE2;
        DDRBALH.BAL = WS_AC_BAL;
        DDRBALH.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRBALH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRBALH.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRBALH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void T000_STARTBR_DDTBALH() throws IOException,SQLException,Exception {
        DDTBALH_BR.rp = new DBParm();
        DDTBALH_BR.rp.TableName = "DDTBALH";
        DDTBALH_BR.rp.where = "AC = :DDRBALH.KEY.AC";
        IBS.STARTBR(SCCGWA, DDRBALH, this, DDTBALH_BR);
    }
    public void S000_CALL_BPZANHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-INQ-FHIS-ACO-AC", BPCANHIS);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            DDCSABLH.RC.RC_AP = "SC";
            DDCSABLH.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
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
        if (DDCSABLH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCSABLH=");
            CEP.TRC(SCCGWA, DDCSABLH);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

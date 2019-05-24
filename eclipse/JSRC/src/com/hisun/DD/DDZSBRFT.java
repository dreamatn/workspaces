package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBRFT {
    DBParm DDTMSTR_RD;
    brParm DDTMSTR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5122";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCS8821";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_PASS_CNT = 0;
    int WS_CNT = 0;
    int WS_END_POS = 0;
    int WS_TOT_NUM = 0;
    char WS_MSTR_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_OVER_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCFA01 DDCFA01 = new DDCFA01();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSBRFT DDCSBRFT;
    public void MP(SCCGWA SCCGWA, DDCSBRFT DDCSBRFT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBRFT = DDCSBRFT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBRFT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CCY_REC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = '8';
        T000_GROUP_DDTMSTR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCFA01);
        WS_STOP_FLG = 'N';
        WS_OVER_FLG = 'N';
        T000_STARTBR_DDTMSTR();
        if (pgmRtn) return;
        T000_READNEXT_DDTMSTR();
        if (pgmRtn) return;
        while (WS_MSTR_FLG != 'N' 
            && WS_STOP_FLG != 'Y') {
            WS_PASS_CNT += 1;
            if (WS_PASS_CNT >= DDCSBRFT.SEQ) {
                WS_CNT += 1;
                B030_OUTPUT_DETAIL();
                if (pgmRtn) return;
                if (WS_CNT >= 20) {
                    WS_STOP_FLG = 'Y';
                }
            }
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTMSTR();
        if (pgmRtn) return;
        if (WS_CNT < 20) {
            WS_OVER_FLG = 'Y';
        }
        WS_END_POS = ( DDCSBRFT.SEQ - 1 ) + WS_CNT;
        if (WS_END_POS == WS_TOT_NUM) {
            WS_OVER_FLG = 'Y';
        }
        B040_OUTPUT_HEAD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DDA01";
        SCCFMT.DATA_PTR = DDCFA01;
        SCCFMT.DATA_LEN = 1488;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_CCY_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSBRFT.CUS_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSBRFT.CCY;
        CICQACAC.DATA.CR_FLG = DDCSBRFT.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        DDCFA01.DETAIL_DATA[WS_CNT-1].STRDT = DDRMSTR.KEY.ADP_STRDATE;
        DDCFA01.DETAIL_DATA[WS_CNT-1].EXPDT = DDRMSTR.ADP_END_DT;
        DDCFA01.DETAIL_DATA[WS_CNT-1].STS = DDRMSTR.ADP_STS;
        DDCFA01.DETAIL_DATA[WS_CNT-1].BAL_AMT = DDRMSTR.BAL_AMT;
        DDCFA01.DETAIL_DATA[WS_CNT-1].FIX_RAT = DDRMSTR.OD_FIX_RATE;
        DDCFA01.DETAIL_DATA[WS_CNT-1].OVR_DAYS = DDRMSTR.OVER_DAYS;
        DDCFA01.DETAIL_DATA[WS_CNT-1].DUE_DAYS = DDRMSTR.DUE_DAYS;
        DDCFA01.DETAIL_DATA[WS_CNT-1].PAND_RAT = DDRMSTR.DUE_RATE;
    }
    public void B040_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        DDCFA01.TOT_NUM = WS_TOT_NUM;
        DDCFA01.REC_NUM = WS_CNT;
        DDCFA01.END_POS = WS_END_POS;
        if (WS_OVER_FLG == 'Y') {
            DDCFA01.END_FLG = 'Y';
        } else {
            DDCFA01.END_FLG = 'N';
        }
    }
    public void T000_GROUP_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.set = "WS-TOT-NUM=COUNT(*)";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        IBS.GROUP(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
    }
    public void T000_STARTBR_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_READNEXT_DDTMSTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTMSTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTMSTR_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLXTZ {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    DBParm DDTMST_RD;
    DBParm DDTMSTR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5122";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSLXTZ";
    String K_HIS_REMARKS = "LI XI TIAO ZHENG ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_AC_TYPE = ' ';
    DDZSLXTZ_WS_OUT_INF WS_OUT_INF = new DDZSLXTZ_WS_OUT_INF();
    char WS_MSTR_FLG = ' ';
    char WS_INTB_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACAC CICQACAC = new CICQACAC();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRMST DDRMST = new DDRMST();
    DDRINTB DDRINTB = new DDRINTB();
    DDRCCY DDRCCY = new DDRCCY();
    DDCHLXTZ DDCHLXTZO = new DDCHLXTZ();
    DDCHLXTZ DDCHLXTZN = new DDCHLXTZ();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSLXTZ DDCSLXTZ;
    public void MP(SCCGWA SCCGWA, DDCSLXTZ DDCSLXTZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLXTZ = DDCSLXTZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLXTZ return!");
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
        B020_GET_ACO_AC_REC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSLXTZ.FUNC);
        if (DDCSLXTZ.FUNC == 'U') {
            B025_CHK_AC_STS();
            if (pgmRtn) return;
            B030_ADJ_INT_PROC();
            if (pgmRtn) return;
            B050_OUTPUT_PROC();
            if (pgmRtn) return;
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        } else if (DDCSLXTZ.FUNC == 'Q') {
            R000_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B030_INQ_INT_PROC();
            if (pgmRtn) return;
            B060_OUTPUT_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_ACO_AC_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSLXTZ.CUS_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSLXTZ.CCY;
        CICQACAC.DATA.CR_FLG = DDCSLXTZ.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_CHK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLXTZ.CUS_AC;
        S000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'V') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'D') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_YEAR_CHK);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ZSZ_UIH_OUT);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_OFFICE_FORBID);
        }
    }
    public void B030_ADJ_INT_PROC() throws IOException,SQLException,Exception {
        if (DDCSLXTZ.DADJ_INT != 0 
            || DDCSLXTZ.SADJ_INT != 0) {
            B030_ADJ_DEP_INT_PROC();
            if (pgmRtn) return;
            T000_REWRITE_DDTINTB();
            if (pgmRtn) return;
        }
        if (DDCSLXTZ.OADJ_INT != 0) {
            B030_ADJ_OVR_INT_PROC();
            if (pgmRtn) return;
            T000_REWRITE_DDTINTB();
            if (pgmRtn) return;
        }
    }
    public void B030_ADJ_DEP_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_UPD_DDTINTB();
        if (pgmRtn) return;
        DDRINTB.DEP_ADJ_INT += DDCSLXTZ.DADJ_INT;
        WS_OUT_INF.WS_ADJD_INT = DDRINTB.DEP_ADJ_INT + DDRINTB.DEP_ACCU_INT;
        if (DDRINTB.DEP_ACCU_INT < DDRINTB.DEP_ADJ_INT * -1) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_INT_LT_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ADJ_OVR_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'O';
        T000_READ_UPD_DDTINTB();
        if (pgmRtn) return;
        DDRINTB.DEP_ADJ_INT = DDCSLXTZ.OADJ_INT;
        WS_OUT_INF.WS_ADJO_INT = DDRINTB.DEP_ADJ_INT + DDRINTB.DEP_ACCU_INT;
        if (DDRINTB.DEP_ACCU_INT < DDRINTB.DEP_ADJ_INT * -1) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OVR_INT_LT_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_INT_PROC() throws IOException,SQLException,Exception {
        B031_GET_DEP_INT();
        if (pgmRtn) return;
        B032_GET_OVR_INT();
        if (pgmRtn) return;
    }
    public void B031_GET_DEP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        if (WS_INTB_FLG == 'F') {
            WS_OUT_INF.WS_DTOT_INT = DDRINTB.DEP_ACCU_INT;
            WS_OUT_INF.WS_DADJ_INT = DDRINTB.DEP_ADJ_INT;
        } else {
            WS_OUT_INF.WS_DTOT_INT = 0;
        }
    }
    public void B032_GET_OVR_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'O';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        if (WS_INTB_FLG == 'F') {
            WS_OUT_INF.WS_OTOT_INT = DDRINTB.DEP_ACCU_INT;
            WS_OUT_INF.WS_OADJ_INT = DDRINTB.DEP_ADJ_INT;
        } else {
            WS_OUT_INF.WS_OTOT_INT = 0;
        }
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        WS_OUT_INF.WS_FUNC = DDCSLXTZ.FUNC;
        WS_OUT_INF.WS_CUS_AC = DDCSLXTZ.CUS_AC;
        WS_OUT_INF.WS_CCY = DDCSLXTZ.CCY;
        WS_OUT_INF.WS_CCY_TYP = DDCSLXTZ.CCY_TYP;
        WS_OUT_INF.WS_DTOT_INT = DDCSLXTZ.DTOT_INT;
        WS_OUT_INF.WS_OTOT_INT = DDCSLXTZ.OTOT_INT;
        WS_OUT_INF.WS_STOT_INT = DDCSLXTZ.STOT_INT;
        WS_OUT_INF.WS_DADJ_INT = DDCSLXTZ.DADJ_INT;
        WS_OUT_INF.WS_OADJ_INT = DDCSLXTZ.OADJ_INT;
        WS_OUT_INF.WS_SADJ_INT = DDCSLXTZ.SADJ_INT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 208;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_OUT_INF.WS_FUNC = DDCSLXTZ.FUNC;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 208;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        R000_NFIN_TX_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHLXTZO);
        DDCHLXTZO.CUS_AC = DDCSLXTZ.CUS_AC;
        DDCHLXTZO.CCY = DDCSLXTZ.CCY;
        DDCHLXTZO.CCY_TYP = DDCSLXTZ.CCY_TYP;
        DDCHLXTZO.DTOT_INT = DDCSLXTZ.DTOT_INT;
        DDCHLXTZO.OTOT_INT = DDCSLXTZ.OTOT_INT;
        DDCHLXTZO.STOT_INT = DDCSLXTZ.STOT_INT;
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHLXTZN);
        DDCHLXTZN.CUS_AC = DDCSLXTZ.CUS_AC;
        DDCHLXTZN.CCY = DDCSLXTZ.CCY;
        DDCHLXTZN.CCY_TYP = DDCSLXTZ.CCY_TYP;
        DDCHLXTZN.DTOT_INT = WS_OUT_INF.WS_ADJD_INT;
        DDCHLXTZN.OTOT_INT = WS_OUT_INF.WS_ADJO_INT;
        DDCHLXTZN.STOT_INT = WS_OUT_INF.WS_ADJS_INT;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 208;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCHLXTZO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCHLXTZN;
        BPCPNHIS.INFO.AC = DDCSLXTZ.CUS_AC;
        BPCPNHIS.INFO.TX_CD = "0118860";
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.upd = true;
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INTB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTB_FLG = 'F';
        } else {
            WS_INTB_FLG = 'N';
        }
    }
    public void S000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        CEP.TRC(SCCGWA, DDRMSTR.ADP_STS);
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_TYPE IN ( '4' , '5' , '6' ) "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_REWRITE_DDTINTB() throws IOException,SQLException,Exception {
        DDRINTB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.REWRITE(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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

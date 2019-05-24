package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIBDCK {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String CPN_BP_P_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String CPN_T_DDTMST = "DD-RSC-DDTMST";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ";
    String CPN_P_CAL_WORK_DAY = "BP-P-CAL-WORK-DAY";
    String CPN_BP_P_CHK_WORK_DAY = "BP-P-CHK-WORK-DAY";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_LAST_BACK_DATE = 0;
    int WS_OPEN_DATE = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPPM1 DDCPPM1 = new DDCPPM1();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIBDCK DDCIBDCK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCIBDCK DDCIBDCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIBDCK = DDCIBDCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIBDCK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        DDCIBDCK.RC.RC_MMO = "DD";
        DDCIBDCK.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACO_AC_PROC();
        if (pgmRtn) return;
        B050_OPEN_DATE_CHK_PROC();
        if (pgmRtn) return;
        B070_BACK_LMT_PARM_CHK_PROC();
        if (pgmRtn) return;
        B090_ADJ_BACK_DATE_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIBDCK.AC_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIBDCK.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCIBDCK.DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCIBDCK.DATE == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_M_INPUT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (DDCIBDCK.DATE >= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID, DDCIBDCK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_GET_ACO_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCIBDCK.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCIBDCK.CCY;
        CICQACAC.DATA.CR_FLG = DDCIBDCK.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B030_WORKINT_DAY_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.CCY = BPCRBANK.LOC_CCY1;
        BPCPGDIN.INPUT_DATA.FUNC = '1';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.DATE_1 = DDCIBDCK.DATE;
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VAL_DT_NOT_WORK_DT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_OPEN_DATE_CHK_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCIBDCK.AC_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIBDCK.AC_NO);
            CEP.TRC(SCCGWA, DDCIBDCK.DATE);
            CEP.TRC(SCCGWA, DDRMST.OPEN_DATE);
            if (DDCIBDCK.DATE < DDRMST.OPEN_DATE) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_LT_OPEN_DT, DDCIBDCK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.OPEN_DATE);
        if (DDCIBDCK.DATE < DDRCCY.OPEN_DATE) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_LT_OPEN_DT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_BACK_LMT_PARM_CHK_PROC() throws IOException,SQLException,Exception {
        R000_GET_BACK_DATE_PERIOD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, DDCPPM1.DATA_TXT.PERIOD);
        SCCCLDT.MTHS = (short) (( -1 ) * DDCPPM1.DATA_TXT.PERIOD);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_LMT_PRM_INVALID, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_LAST_BACK_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (DDCIBDCK.DATE < WS_LAST_BACK_DATE) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_EXCEED_LMT, DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B090_ADJ_BACK_DATE_PROC() throws IOException,SQLException,Exception {
        DDCIBDCK.BK_DT = DDCIBDCK.DATE;
        CEP.TRC(SCCGWA, DDCIBDCK.DATE);
        CEP.TRC(SCCGWA, DDCIBDCK.LAST_POST_DATE);
    }
    public void R000_GET_CAL_CODE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '1';
        BPCOQCAL.CCY = DDCIBDCK.CCY;
        S000_CALL_BPZOQCAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOQCAL.CAL_CODE);
    }
    public void R000_GET_NXT_ACDT_OF_LAST_PDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCLWD.DATE1 = DDCIBDCK.LAST_POST_DATE;
        BPCOCLWD.DAYS = 2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void R000_GET_BACK_DATE_PERIOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCPPM1);
        IBS.init(SCCGWA, BPCPRMR);
        DDCPPM1.KEY.TYP = "PDD07";
        DDCPPM1.KEY.CD = "BDP";
        CEP.TRC(SCCGWA, DDCPPM1.KEY);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        CEP.TRC(SCCGWA, BPCPGDIN.RC);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZOQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_P_INQ_CAL_CODE, BPCOQCAL);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_P_CHK_WORK_DAY, BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DDCPPM1;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CAL_WORK_DAY, BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBDCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIBDCK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIBDCK=");
            CEP.TRC(SCCGWA, DDCIBDCK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

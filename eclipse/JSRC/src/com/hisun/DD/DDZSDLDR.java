package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCRIAACR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDRSMST;

public class DDZSDLDR {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    String WS_HLD_AC = " ";
    String WS_HLD_NO2 = " ";
    String WS_ACO_AC = " ";
    int WS_EFF_DATE = 0;
    char WS_HLDR_FLG = ' ';
    String WS_CARD_NO = " ";
    DDZSDLDR_WS_OUTPUT_HIS_HLD WS_OUTPUT_HIS_HLD = new DDZSDLDR_WS_OUTPUT_HIS_HLD();
    char WS_DCTIAACR_REC = ' ';
    char WS_DCTHLDR_REC = ' ';
    char WS_DDTCCY_FLG = ' ';
    char WS_BROWSE_FUNC = ' ';
    char WS_HLD_NO_FLG = ' ';
    char WS_HLD_READ_FLG = ' ';
    char WS_TDTSMST_FLG = ' ';
    int WS_FO_AC_DATE = 0;
    int WS_TO_AC_DATE = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DDRHLDR DDRHLDR = new DDRHLDR();
    CICQACRI CICQACRI = new CICQACRI();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    CICQACAC CICQACAC = new CICQACAC();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    DCCSHLDR DCCSHLDR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSHLDR DCCSHLDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSHLDR = DCCSHLDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDLDR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (DCCSHLDR.AC.trim().length() > 0) {
            B020_GET_CI_AC_INFO();
            if (pgmRtn) return;
        }
        B040_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B050_QUR_HIS_HLD_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSHLDR.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLDR.FO_AC_DATE == 0) {
            if ("00000000".trim().length() == 0) DCCSHLDR.FO_AC_DATE = 0;
            else DCCSHLDR.FO_AC_DATE = Integer.parseInt("00000000");
        }
        if (DCCSHLDR.TO_AC_DATE == 0) {
            if ("99991231".trim().length() == 0) DCCSHLDR.TO_AC_DATE = 0;
            else DCCSHLDR.TO_AC_DATE = Integer.parseInt("99991231");
        }
    }
    public void B020_GET_CI_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        WS_ACO_AC = " ";
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSHLDR.AC;
        WS_CARD_NO = DCCSHLDR.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CARD_NO);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_CARD_NO;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_CARD_NO = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSHLDR.CCY);
        CEP.TRC(SCCGWA, DCCSHLDR.CCY_TYPE);
        if (DCCSHLDR.SEQ != 0 
            || DCCSHLDR.BV_NO.trim().length() > 0 
            || (DCCSHLDR.CCY.trim().length() > 0 
            && DCCSHLDR.CCY_TYPE != ' ')) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = WS_CARD_NO;
            CICQACAC.DATA.AGR_SEQ = DCCSHLDR.SEQ;
            CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
            CICQACAC.DATA.BV_NO = DCCSHLDR.BV_NO;
            CICQACAC.DATA.CCY_ACAC = DCCSHLDR.CCY;
            CICQACAC.DATA.CR_FLG = DCCSHLDR.CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
    }
    public void B040_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 856;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_QUR_HIS_HLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        WS_FO_AC_DATE = DCCSHLDR.FO_AC_DATE;
        WS_TO_AC_DATE = DCCSHLDR.TO_AC_DATE;
        if (WS_ACO_AC.trim().length() == 0) {
            CEP.TRC(SCCGWA, DCCSHLDR.SEQ);
            CEP.TRC(SCCGWA, DCCSHLDR.BV_NO);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = WS_CARD_NO;
                T000_STARTBR_DDTCCY_CUSAC();
                if (pgmRtn) return;
                T000_READNXT_DDTCCY();
                if (pgmRtn) return;
                while (WS_DDTCCY_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    DDRHLDR.AC = DDRCCY.KEY.AC;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.ACAC_NO = DDRHLDR.AC;
                    CICQACAC.FUNC = 'A';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    WS_BROWSE_FUNC = 'A';
                    R000_BROWSE_DDTHLDR();
                    if (pgmRtn) return;
                    T000_READNXT_DDTCCY();
                    if (pgmRtn) return;
                }
                T000_ENDBR_DDTCCY();
                if (pgmRtn) return;
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
                || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = DCCSHLDR.AC;
                T000_STARTBR_TDTSMST_CUSAC();
                if (pgmRtn) return;
                T000_READNXT_TDTSMST();
                if (pgmRtn) return;
                while (WS_TDTSMST_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    DDRHLDR.AC = TDRSMST.KEY.ACO_AC;
                    WS_BROWSE_FUNC = 'A';
                    R000_BROWSE_DDTHLDR();
                    if (pgmRtn) return;
                    T000_READNXT_TDTSMST();
                    if (pgmRtn) return;
                }
                T000_ENDBR_TDTSMST();
                if (pgmRtn) return;
            }
        }
        if (WS_ACO_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_ACO_AC);
            DDRHLDR.AC = WS_ACO_AC;
            WS_BROWSE_FUNC = 'A';
            R000_BROWSE_DDTHLDR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_HLDR_FLG);
        if (WS_HLDR_FLG != '1') {
            CEP.TRC(SCCGWA, "11111111");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
        }
    }
    public void R000_BROWSE_DDTHLDR() throws IOException,SQLException,Exception {
        T000_STARTBR_DCTHLDR_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_DCTHLDR();
        if (pgmRtn) return;
        while (WS_DCTHLDR_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B070_OUTPUT_HIS_HLD_PROC();
            if (pgmRtn) return;
            T000_READNEXT_DCTHLDR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTHLDR();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_HIS_HLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_HIS_HLD);
        WS_OUTPUT_HIS_HLD.WS_HLD_NO = DDRHLDR.KEY.HLD_NO;
        WS_OUTPUT_HIS_HLD.WS_AC_DATE = DDRHLDR.KEY.TR_DATE;
        WS_OUTPUT_HIS_HLD.WS_HLD_TYP = DDRHLDR.HLD_TYP;
        if (DDRHLDR.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.ACAC_NO = DDRHLDR.AC;
            CICQACAC.FUNC = 'A';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_OUTPUT_HIS_HLD.WS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_OUTPUT_HIS_HLD.WS_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_OUTPUT_HIS_HLD.WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
            WS_OUTPUT_HIS_HLD.WS_CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        }
        WS_OUTPUT_HIS_HLD.WS_SPR_TYP = DDRHLDR.SPR_BR_TYP;
        WS_OUTPUT_HIS_HLD.WS_SPR_NM = DDRHLDR.SPR_BR_NM;
        WS_OUTPUT_HIS_HLD.WS_BEF_AMT = DDRHLDR.BEF_TR_AMT;
        WS_OUTPUT_HIS_HLD.WS_AMT = DDRHLDR.TR_AMT;
        WS_OUTPUT_HIS_HLD.WS_SPR_NO = DDRHLDR.CHG_WRIT_NO;
        WS_OUTPUT_HIS_HLD.WS_CHG_RSN = DDRHLDR.CHG_RSN;
        WS_OUTPUT_HIS_HLD.WS_DED_FLG = DDRHLDR.DEDUCT_FLG;

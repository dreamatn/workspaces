package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQPOC {
    brParm DDTOCAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_FLG = " ";
    char WS_ROCAC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOQPOC DDCOQPOC = new DDCOQPOC();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    int WS_DB_START_DATE = 0;
    int WS_DB_END_DATE = 0;
    DDROCAC DDROCAC = new DDROCAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSQPOC DDCSQPOC;
    public void MP(SCCGWA SCCGWA, DDCSQPOC DDCSQPOC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQPOC = DDCSQPOC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQPOC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDCOQPOC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, DDCSQPOC.TLR_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.BANK_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.START_DATE);
        CEP.TRC(SCCGWA, DDCSQPOC.END_DATE);
        CEP.TRC(SCCGWA, DDCSQPOC.INQU_TYPE);
        CEP.TRC(SCCGWA, DDCSQPOC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.PSBK_NO);
        if (DDCSQPOC.START_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSQPOC.START_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSQPOC.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSQPOC.END_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DT2_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSQPOC.START_DATE > DDCSQPOC.END_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DATE_LT_FROM_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B030_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        if (DDCSQPOC.CARD_NO.trim().length() > 0) {
            T000_STARTBR_DDTOCAC_08();
            if (pgmRtn) return;
        } else {
            if (DDCSQPOC.START_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSQPOC.END_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSQPOC.TLR_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TLR_NO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSQPOC.BANK_NO == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_NO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_STARTBR_DDTOCAC_07();
            if (pgmRtn) return;
        }
        B050_READNEXT_DDTOCAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (WS_ROCAC_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_CHECK_INQTYPE();
            if (pgmRtn) return;
            B050_READNEXT_DDTOCAC();
            if (pgmRtn) return;
        }
        B070_ENDBR_DDTOCAC();
        if (pgmRtn) return;
    }
    public void B030_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 99;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_DDTOCAC() throws IOException,SQLException,Exception {
        if (DDCSQPOC.TLR_NO.trim().length() > 0) {
            WS_FLG = "01";
        }
        if (DDCSQPOC.BANK_NO != 0) {
            WS_FLG = "02";
        }
        if (DDCSQPOC.START_DATE != 0) {
            WS_FLG = "03";
        }
        if (DDCSQPOC.START_DATE != 0 
            && DDCSQPOC.END_DATE != 0) {
            WS_FLG = "03";
        }
        if (DDCSQPOC.TLR_NO.trim().length() > 0 
            && DDCSQPOC.BANK_NO != 0) {
            WS_FLG = "04";
        }
        if (DDCSQPOC.START_DATE != 0 
            && DDCSQPOC.END_DATE != 0 
            && DDCSQPOC.TLR_NO.trim().length() > 0) {
            WS_FLG = "05";
        }
        if (DDCSQPOC.START_DATE != 0 
            && DDCSQPOC.END_DATE != 0 
            && DDCSQPOC.BANK_NO != 0) {
            WS_FLG = "06";
        }
        if (DDCSQPOC.START_DATE != 0 
            && DDCSQPOC.END_DATE != 0 
            && DDCSQPOC.TLR_NO.trim().length() > 0 
            && DDCSQPOC.BANK_NO != 0) {
            WS_FLG = "07";
        }
        if (DDCSQPOC.CARD_NO.trim().length() > 0) {
            WS_FLG = "08";
        }
        if (DDCSQPOC.PSBK_NO.trim().length() > 0) {
            WS_FLG = "09";
        }
        IBS.init(SCCGWA, DDROCAC);
        CEP.TRC(SCCGWA, "11111");
        if (WS_FLG.equalsIgnoreCase("07")) {
            CEP.TRC(SCCGWA, "77777777");
            T000_STARTBR_DDTOCAC_07();
            if (pgmRtn) return;
        } else if (WS_FLG.equalsIgnoreCase("08")) {
            T000_STARTBR_DDTOCAC_08();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_IPUT_ONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTOCAC_07() throws IOException,SQLException,Exception {
        DDROCAC.CRT_TLR = DDCSQPOC.TLR_NO;
        DDROCAC.BR = DDCSQPOC.BANK_NO;
        WS_DB_START_DATE = DDCSQPOC.START_DATE;
        WS_DB_END_DATE = DDCSQPOC.END_DATE;
        DDTOCAC_BR.rp = new DBParm();
        DDTOCAC_BR.rp.TableName = "DDTOCAC";
        DDTOCAC_BR.rp.where = "CRT_TLR = :DDROCAC.CRT_TLR "
            + "AND BR = :DDROCAC.BR "
            + "AND AC_DATE >= :WS_DB_START_DATE "
            + "AND AC_DATE <= :WS_DB_END_DATE";
        IBS.STARTBR(SCCGWA, DDROCAC, this, DDTOCAC_BR);
    }
    public void T000_STARTBR_DDTOCAC_08() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSQPOC.CARD_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        DDROCAC.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDROCAC.KEY.AC);
        DDTOCAC_BR.rp = new DBParm();
        DDTOCAC_BR.rp.TableName = "DDTOCAC";
        DDTOCAC_BR.rp.where = "AC = :DDROCAC.KEY.AC";
        IBS.STARTBR(SCCGWA, DDROCAC, this, DDTOCAC_BR);
    }
    public void B050_READNEXT_DDTOCAC() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTOCAC();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_DDTOCAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDROCAC, this, DDTOCAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ROCAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ROCAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTOCAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B060_CHECK_INQTYPE() throws IOException,SQLException,Exception {
        if (DDCSQPOC.INQU_TYPE == ' ') {
            B080_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
        if (DDCSQPOC.INQU_TYPE == 'O' 
            && DDROCAC.KEY.STS == 'N') {
            B080_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
        if (DDCSQPOC.INQU_TYPE == 'C' 
            && DDROCAC.KEY.STS == 'C') {
            B080_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
    }
    public void B080_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOQPOC);
        DDCOQPOC.AC_NO = DDROCAC.KEY.AC;
        DDCOQPOC.CARD_NO = DDROCAC.CARD_NO;
        DDCOQPOC.OPCL_DATE = DDROCAC.AC_DATE;
        DDCOQPOC.PROD_CD = DDROCAC.PROD_CD;
        DDCOQPOC.CCY_TYPE = DDROCAC.CCY;
        DDCOQPOC.TLR_NO = DDROCAC.CRT_TLR;
        DDCOQPOC.CARD_FLG = DDROCAC.CARD_FLG;
        DDCOQPOC.VCH_NO = DDROCAC.VCH_NO;
        DDCOQPOC.MEDI_TYPE = DDROCAC.VCH_TYPE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOQPOC);
        SCCMPAG.DATA_LEN = 99;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B070_ENDBR_DDTOCAC() throws IOException,SQLException,Exception {
        T000_ENDBR_DDTOCAC();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_DDTOCAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTOCAC_BR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCSQPOC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCSQPOC=");
            CEP.TRC(SCCGWA, DDCSQPOC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

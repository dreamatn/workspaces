package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCICG {
    brParm DCTCDDAT_BR = new brParm();
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TBL_CDDAT = "DCTCDDAT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_CARD_CINO = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUCICG DCCUCICG;
    public void MP(SCCGWA SCCGWA, DCCUCICG DCCUCICG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCICG = DCCUCICG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCICG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_MODIFY_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCUCICG.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT);
        }
        if (DCCUCICG.CI_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT);
        }
        if (DCCUCICG.NEW_CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NEW_CI_NO_M_INPUT, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NEW_CI_NO_M_INPUT);
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCICG.CI_NO);
        CEP.TRC(SCCGWA, DCCUCICG.CI_TYP);
        if (DCCUCICG.CI_TYP == 'C') {
            B021_CI_CO_PROC();
            if (pgmRtn) return;
        } else if (DCCUCICG.CI_TYP == 'P') {
            B022_CI_PER_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_TYP_NOT_KNOWN, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_TYP_NOT_KNOWN);
        }
    }
    public void B021_CI_CO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        WS_CARD_CINO = DCCUCICG.CI_NO;
        T000_STARTBR_DCTCDDAT_CO();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG != 'N') {
            WS_CNT = 0;
            while (WS_TBL_FLAG != 'N') {
                WS_CNT += 1;
                DCRCDDAT.CARD_OWN_CINO = DCCUCICG.NEW_CI_NO;
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCDDAT();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'D') {
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
                }
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void B022_CI_PER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        WS_CARD_CINO = DCCUCICG.CI_NO;
        T000_STARTBR_DCTCDDAT_PER();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG != 'N') {
            WS_CNT = 0;
            while (WS_TBL_FLAG != 'N') {
                WS_CNT += 1;
                DCRCDDAT.CARD_OWN_CINO = DCCUCICG.NEW_CI_NO;
                DCRCDDAT.CARD_HLDR_CINO = DCCUCICG.NEW_CI_NO;
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCDDAT();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'D') {
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
                }
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCUCICG.RC);
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_DCTCDDAT_CO() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_OWN_CINO = :WS_CARD_CINO";
        DCTCDDAT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTCDDAT_PER() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :WS_CARD_CINO";
        DCTCDDAT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

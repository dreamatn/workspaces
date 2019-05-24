package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUSPAC {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTSPAC_RD;
    brParm DCTSPAC_BR = new brParm();
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_I = 0;
    char WS_SPAC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    DCCUSPAC DCCUSPAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DCCUSPAC DCCUSPAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUSPAC = DCCUSPAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUSPAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_INQ_AC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DCCUSPAC.FUNC.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRSPAC);
        DCRSPAC.KEY.FREE_AC = DCCUSPAC.FUNC.AC;
        T000_READ_DCTSPAC();
        if (pgmRtn) return;
        if (WS_SPAC_FLG == 'Y') {
            if (DCRSPAC.EFF_FLG == '1') {
                DCCUSPAC.OUTPUT.FREE_NO[1-1].FREE_AC = DCRSPAC.KEY.FREE_AC;
                DCCUSPAC.OUTPUT.FREE_NO[1-1].FREE_TYPE = DCRSPAC.FREE_TYPE;
                DCCUSPAC.OUTPUT.STD_AC = DCRSPAC.STD_AC;
                DCCUSPAC.OUTPUT.AC_TYPE = '0';
            }
        } else {
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.STD_AC = DCCUSPAC.FUNC.AC;
            CEP.TRC(SCCGWA, DCCUSPAC.FUNC.AC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if ((JIBS_tmp_str[0].equalsIgnoreCase("0028100") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0028102"))) {
                T000_STARTBR_DCTSPAC_FOR_CI();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_DCTSPAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTSPAC();
            if (pgmRtn) return;
            for (WS_IDX = 1; WS_SPAC_FLG != 'N' 
                && WS_IDX <= 99; WS_IDX += 1) {
                CEP.TRC(SCCGWA, "WS-I11");
                CEP.TRC(SCCGWA, WS_I);
                WS_I = (short) (WS_I + 1);
                DCCUSPAC.OUTPUT.FREE_NO[WS_IDX-1].FREE_AC = DCRSPAC.KEY.FREE_AC;
                DCCUSPAC.OUTPUT.FREE_NO[WS_IDX-1].FREE_TYPE = DCRSPAC.FREE_TYPE;
                CEP.TRC(SCCGWA, DCRSPAC.FREE_TYPE);
                DCCUSPAC.OUTPUT.STD_AC = DCRSPAC.STD_AC;
                CEP.TRC(SCCGWA, DCRSPAC.STD_AC);
                T000_READNEXT_DCTSPAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "WS-I:");
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, "WS-IDX:");
                CEP.TRC(SCCGWA, WS_IDX);
            }
            T000_ENDBR_DCTSPAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_I);
            if (WS_I > 0) {
                DCCUSPAC.OUTPUT.AC_TYPE = '1';
            } else {
                DCCUSPAC.OUTPUT.AC_TYPE = '2';
            }
            DCCUSPAC.OUTPUT.FREE_AC_NUM = WS_I;
        }
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SPAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SPAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ SCTSPAC ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_BR.rp = new DBParm();
        DCTSPAC_BR.rp.TableName = "DCTSPAC";
        DCTSPAC_BR.rp.where = "STD_AC = :DCRSPAC.STD_AC "
            + "AND EFF_FLG = '1'";
        IBS.STARTBR(SCCGWA, DCRSPAC, this, DCTSPAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SPAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SPAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR DCTSPAC ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTSPAC_FOR_CI() throws IOException,SQLException,Exception {
        DCTSPAC_BR.rp = new DBParm();
        DCTSPAC_BR.rp.TableName = "DCTSPAC";
        DCTSPAC_BR.rp.where = "STD_AC = :DCRSPAC.STD_AC "
            + "AND ( FREE_TYPE = '002' "
            + "OR FREE_TYPE = '007' ) "
            + "AND EFF_FLG = '1'";
        IBS.STARTBR(SCCGWA, DCRSPAC, this, DCTSPAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SPAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SPAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR DCTSPAC(FOR CI) ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTSPAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRSPAC, this, DCTSPAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_SPAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_SPAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR DCTSPAC ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTSPAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTSPAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ENDBR DCTSPAC ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

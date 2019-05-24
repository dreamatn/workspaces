package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUIQMC {
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAMST_RD;
    DBParm DCTACLNK_RD;
    brParm DCTACLNK_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZUSPAC = "DC-INQ-STD-AC";
    char WS_TBL_FLAG_IAACR = ' ';
    char WS_TBL_FLAG_ACLNK = ' ';
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    String WS_IAACR_SUB_AC = " ";
    String WS_IAACR_VIA_AC = " ";
    SCCGWA SCCGWA;
    DCCUIQMC DCCUIQMC;
    public void MP(SCCGWA SCCGWA, DCCUIQMC DCCUIQMC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUIQMC = DCCUIQMC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUIQMC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_MSG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUIQMC.INP_DATA.AC);
        if (DCCUIQMC.INP_DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_MSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DCCUIQMC.INP_DATA.AC;
        WS_IAACR_SUB_AC = DCCUIQMC.INP_DATA.AC;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "0000000");
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "00001111");
        while (WS_TBL_FLAG_IAACR != 'N') {
            IBS.init(SCCGWA, DCRIAMST);
            DCRIAMST.KEY.VIA_AC = DCRIAACR.KEY.VIA_AC;
            T000_READ_DCTIAMST();
            if (pgmRtn) return;
            WS_I = (short) (WS_I + 1);
            if (WS_I > 100) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RL_NUM_GT_MAX_NUM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRIAMST.VIA_FLG == '0') {
                DCCUIQMC.OUT_DATA[WS_I-1].IA_ACOUNT = DCRIAMST.KEY.VIA_AC;
            } else {
                DCCUIQMC.OUT_DATA[WS_I-1].VA_ACOUNT = DCRIAMST.KEY.VIA_AC;
            }
            DCCUIQMC.OUT_DATA[WS_I-1].SEQ = DCRIAACR.KEY.SEQ;
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.VIA_AC = DCRIAACR.KEY.VIA_AC;
            CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
            T000_STARTBR_DCTACLNK();
            if (pgmRtn) return;
            T000_READNEXT_DCTACLNK();
            if (pgmRtn) return;
            while (WS_TBL_FLAG_ACLNK != 'N') {
                if (DCRIAMST.VIA_FLG == '1') {
                    DCCUIQMC.OUT_DATA[WS_I-1].VA_CARD_NO = DCRACLNK.KEY.CARD_NO;
                } else {
                    DCCUIQMC.OUT_DATA[WS_I-1].IA_CARD_NO = DCRACLNK.KEY.CARD_NO;
                }
                T000_READNEXT_DCTACLNK();
                if (pgmRtn) return;
                if (WS_TBL_FLAG_ACLNK == 'Y') {
                    WS_I = (short) (WS_I + 1);
                    if (WS_I > 100) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RL_NUM_GT_MAX_NUM;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DCRIAMST.VIA_FLG == '0') {
                        DCCUIQMC.OUT_DATA[WS_I-1].IA_ACOUNT = DCRIAMST.KEY.VIA_AC;
                    } else {
                        DCCUIQMC.OUT_DATA[WS_I-1].VA_ACOUNT = DCRIAMST.KEY.VIA_AC;
                    }
                    DCCUIQMC.OUT_DATA[WS_I-1].SEQ = DCRIAACR.KEY.SEQ;
                }
            }
            T000_ENDBR_DCTACLNK();
            if (pgmRtn) return;
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :WS_IAACR_SUB_AC "
            + "AND ACCR_FLG = '1'";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_IAACR = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_IAACR = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_IAACR = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_IAACR = 'N';
        } else {
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IA_MST_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.where = "VIA_AC = :DCRACLNK.VIA_AC "
            + "AND CARD_AC_STATUS = '1'";
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_ACLNK = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_ACLNK = 'N';
        } else {
        }
    }
    public void T000_STARTBR_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_BR.rp = new DBParm();
        DCTACLNK_BR.rp.TableName = "DCTACLNK";
        DCTACLNK_BR.rp.where = "VIA_AC = :DCRACLNK.VIA_AC "
            + "AND CARD_AC_STATUS = '1'";
        IBS.STARTBR(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_ACLNK = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_ACLNK = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DCRACLNK.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_ACLNK = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_ACLNK = 'N';
        } else {
        }
    }
    public void T000_ENDBR_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTACLNK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUSPAC, DCCUSPAC);
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

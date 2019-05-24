package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.EA.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUBIND {
    DBParm EATACLNK_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ACLNK_CON_AC = "                                ";
    String WS_ACLNK_CARD_NO = "                   ";
    String WS_ACLNK_CON_BNK = "              ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_H_CARD_NO = " ";
    char WS_H_CHG_STS = ' ';
    int WS_H_HDOV_DT = 0;
    int WS_H_HDOV_BR = 0;
    String WS_H_HDOV_TLR = " ";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_PROD_DES = "                                                            ";
    String WS_FMT_CDE = "     ";
    short WS_I = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    EACSBINQ EACSBINQ = new EACSBINQ();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    EARACLNK EARACLNK = new EARACLNK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUBIND DCCUBIND;
    public void MP(SCCGWA SCCGWA, DCCUBIND DCCUBIND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUBIND = DCCUBIND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUBIND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (DCCUBIND.IO_AREA.FUNC == 'H') {
            B100_PLAN_CHANGE();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_RSLT);
        }
    }
    public void B010_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUBIND.IO_AREA.CARD_NEW);
        CEP.TRC(SCCGWA, DCCUBIND.IO_AREA.CARD_OLD);
        CEP.TRC(SCCGWA, DCCUBIND.IO_AREA.FUNC);
        CEP.TRC(SCCGWA, DCCUBIND.IO_AREA.AC_TYP);
        if (DCCUBIND.IO_AREA.CARD_NEW.trim().length() == 0 
            || DCCUBIND.IO_AREA.CARD_OLD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCUBIND.IO_AREA.FUNC == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT);
        }
        if (DCCUBIND.IO_AREA.AC_TYP == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYP_M_INPUT);
        }
    }
    public void B100_PLAN_CHANGE() throws IOException,SQLException,Exception {
        if (DCCUBIND.IO_AREA.AC_TYP == '1') {
            IBS.init(SCCGWA, EACSBINQ);
            EACSBINQ.I_AC = DCCUBIND.IO_AREA.CARD_OLD;
            S000_CALL_EAZSBINQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
            for (WS_I = 1; WS_I <= 5 
                && EACSBINQ.AC_ARRAY[WS_I-1].CON_AC.trim().length() != 0; WS_I += 1) {
                WS_ACLNK_CARD_NO = EACSBINQ.AC_ARRAY[WS_I-1].CON_AC;
                WS_ACLNK_CON_AC = DCCUBIND.IO_AREA.CARD_OLD;
                WS_ACLNK_CON_BNK = EACSBINQ.AC_ARRAY[WS_I-1].CON_BNK;
                T000_READUPD_EATACLNK();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    EARACLNK.KEY.CON_AC = DCCUBIND.IO_AREA.CARD_NEW;
                    EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T100_UPDATE_EATACLNK();
                    if (pgmRtn) return;
                }
            }
        }
        if (DCCUBIND.IO_AREA.AC_TYP == '2') {
            IBS.init(SCCGWA, EACSBINQ);
            EACSBINQ.CARD_NO = DCCUBIND.IO_AREA.CARD_OLD;
            S000_CALL_EAZSBINQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
            CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
            for (WS_I = 1; WS_I <= 5 
                && EACSBINQ.AC_ARRAY[WS_I-1].CON_AC.trim().length() != 0; WS_I += 1) {
                WS_ACLNK_CON_AC = EACSBINQ.AC_ARRAY[WS_I-1].CON_AC;
                WS_ACLNK_CARD_NO = DCCUBIND.IO_AREA.CARD_OLD;
                WS_ACLNK_CON_BNK = EACSBINQ.AC_ARRAY[WS_I-1].CON_BNK;
                T000_READUPD_EATACLNK();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    EARACLNK.KEY.CARD_NO = DCCUBIND.IO_AREA.CARD_NEW;
                    EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T100_UPDATE_EATACLNK();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_CALL_EAZSBINQ() throws IOException,SQLException,Exception {
        EAZSBINQ EAZSBINQ = new EAZSBINQ();
        EAZSBINQ.MP(SCCGWA, EACSBINQ);
    }
    public void T000_READUPD_EATACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACLNK_CARD_NO);
        CEP.TRC(SCCGWA, WS_ACLNK_CON_AC);
        CEP.TRC(SCCGWA, WS_ACLNK_CON_BNK);
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.upd = true;
        EATACLNK_RD.where = "CARD_NO = :WS_ACLNK_CARD_NO "
            + "AND CON_AC = :WS_ACLNK_CON_AC "
            + "AND CON_BNK = :WS_ACLNK_CON_BNK";
        IBS.READ(SCCGWA, EARACLNK, this, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, EARACLNK.KEY.CON_AC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T100_UPDATE_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.REWRITE(SCCGWA, EARACLNK, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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

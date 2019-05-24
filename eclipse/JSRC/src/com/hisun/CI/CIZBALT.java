package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBALT {
    DBParm CITALH_RD;
    brParm CITALT_BR = new brParm();
    DBParm CITALT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_CNT = 12;
    String WS_ERR_MSG = " ";
    char WS_CHECK_FLG = ' ';
    char WS_DUPL_FLG = ' ';
    char WS_TITLE_FLG = ' ';
    char WS_REC_FLG = ' ';
    char WS_ALB_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRALH CIRALH = new CIRALH();
    CIRALH CIRALHN = new CIRALH();
    CIRALT CIRALT = new CIRALT();
    CICOALBL CICOALBL = new CICOALBL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBALT CICBALT;
    public void MP(SCCGWA SCCGWA, CICBALT CICBALT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBALT = CICBALT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBALT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_CHECK_FLG = ' ';
        WS_TITLE_FLG = ' ';
        CICBALT.REMIND_FLG = 'N';
        if (CICBALT.OUTPUT_FLG == ' ') {
            CICBALT.OUTPUT_FLG = 'Y';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CICBALT.OUTPUT_FLG == 'Y') {
            B010_01_OUT_GWA_CHNL();
            if (pgmRtn) return;
            B010_02_CHK_SPC_CHNL();
            if (pgmRtn) return;
            if (WS_REC_FLG == 'Y') {
                B010_02_OUT_SPC_CHNL();
                if (pgmRtn) return;
            }
        } else {
            CICBALT.REMIND_FLG = 'N';
            B020_CHK_ALT_FIRST();
            if (pgmRtn) return;
            if (WS_REC_FLG == 'Y') {
                B022_SET_REMIND_FLG();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_01_OUT_GWA_CHNL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CIRALT.ENTY_TYP = CICBALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICBALT.DATA.ENTY_NO;
        CIRALT.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_STARTBR_CITALT();
        if (pgmRtn) return;
        T000_READNEXT_CITALT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CICBALT.OUTPUT_FLG == 'Y') {
                B080_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                B021_CHECK_CI_RECORD();
                if (pgmRtn) return;
                if (WS_CHECK_FLG == 'Y' 
                    && CICBALT.OUTPUT_FLG == 'Y') {
                    B080_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITALT();
                if (pgmRtn) return;
            }
        } else {
            WS_TITLE_FLG = 'N';
        }
        T000_ENDBR_CITALT();
        if (pgmRtn) return;
    }
    public void B010_02_CHK_SPC_CHNL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.ENTY_TYP = CICBALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICBALT.DATA.ENTY_NO;
        CIRALT.MSG_CHNL = " ";
        T000_READ_CITALT();
        if (pgmRtn) return;
    }
    public void B010_02_OUT_SPC_CHNL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.ENTY_TYP = CICBALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICBALT.DATA.ENTY_NO;
        CIRALT.MSG_CHNL = " ";
        CEP.TRC(SCCGWA, CIRALT.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRALT.ENTY_NO);
        CEP.TRC(SCCGWA, CIRALT.MSG_CHNL);
        T000_STARTBR_CITALT();
        if (pgmRtn) return;
        T000_READNEXT_CITALT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (WS_TITLE_FLG == 'N' 
                && CICBALT.OUTPUT_FLG != 'N') {
                B080_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                B021_CHECK_CI_RECORD();
                if (pgmRtn) return;
                if (WS_CHECK_FLG == 'Y' 
                    && CICBALT.OUTPUT_FLG != 'N') {
                    B080_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITALT();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_CITALT();
        if (pgmRtn) return;
    }
    public void B020_CHK_ALT_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.ENTY_TYP = CICBALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICBALT.DATA.ENTY_NO;
        T000_READ_CITALT_FIRST();
        if (pgmRtn) return;
    }
    public void B022_SET_REMIND_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.ENTY_TYP = CICBALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICBALT.DATA.ENTY_NO;
        CIRALT.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_STARTBR_CITALT_2();
        if (pgmRtn) return;
        T000_READNEXT_CITALT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && CICBALT.REMIND_FLG != 'Y') {
            B021_CHECK_CI_RECORD();
            if (pgmRtn) return;
            T000_READNEXT_CITALT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITALT();
        if (pgmRtn) return;
    }
    public void B021_CHECK_CI_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALH);
        CIRALH.KEY.ALT_NO = CIRALT.KEY.ALT_NO;
        CIRALH.KEY.ENTY_TYP = CIRALT.ENTY_TYP;
        CIRALH.KEY.ENTY_NO = CIRALT.ENTY_NO;
        CIRALH.KEY.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_READ_CITALH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHECK_FLG = 'Y';
            CICBALT.REMIND_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRALH.STOP == 'N') {
                WS_CHECK_FLG = 'Y';
            } else {
                WS_CHECK_FLG = 'N';
            }
        }
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOALBL);
        CICOALBL.ALT_NO = CIRALT.KEY.ALT_NO;
        CICOALBL.ENTY_TYP = CIRALT.ENTY_TYP;
        CICOALBL.ENTY_NO = CIRALT.ENTY_NO;
        CICOALBL.ALT_TYP = CIRALT.ALT_TYP;
        CICOALBL.MSG_CHNL = CIRALT.MSG_CHNL;
        CICOALBL.REMARK = CIRALT.REMARK;
        CEP.TRC(SCCGWA, CICOALBL);
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, CICOALBL);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOALBL);
        SCCMPAG.DATA_LEN = 178;
        CEP.TRC(SCCGWA, CICOALBL);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_CITALH() throws IOException,SQLException,Exception {
        CITALH_RD = new DBParm();
        CITALH_RD.TableName = "CITALH";
        IBS.READ(SCCGWA, CIRALH, CITALH_RD);
    }
    public void T000_STARTBR_CITALT() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO,MSG_CHNL";
        CITALT_BR.rp.order = "ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRALT, CITALT_BR);
    }
    public void T000_STARTBR_CITALT_2() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND MSG_CHNL = :CIRALT.MSG_CHNL";
        IBS.STARTBR(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_READ_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.eqWhere = "ENTY_TYP,ENTY_NO,MSG_CHNL";
        CITALT_RD.fst = true;
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else {
            WS_REC_FLG = 'N';
        }
    }
    public void T000_READ_CITALT_FIRST() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.eqWhere = "ENTY_TYP,ENTY_NO";
        CITALT_RD.fst = true;
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else {
            WS_REC_FLG = 'N';
        }
    }
    public void T000_READNEXT_CITALT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_ENDBR_CITALT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITALT_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
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
        if (CICBALT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICBALT=");
            CEP.TRC(SCCGWA, CICBALT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCHDC {
    brParm TDTSMST_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTINST_RD;
    DBParm TDTCDI_RD;
    DBParm TDTMAGT_RD;
    brParm TDTINST_BR = new brParm();
    brParm TDTCDI_BR = new brParm();
    brParm TDTMAGT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_INQ_MACE_FMT = "TD523";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_BVT_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_INST_FLG = ' ';
    char WS_CDI_FLG = ' ';
    char WS_MAGT_FLG = ' ';
    short WS_TIME = 0;
    TDZCHDC_WS_TABLES_INFO WS_TABLES_INFO = new TDZCHDC_WS_TABLES_INFO();
    char WS_IAMST_FLG = ' ';
    TDZCHDC_WS_OUT_INF WS_OUT_INF = new TDZCHDC_WS_OUT_INF();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRBVT TDRBVT = new TDRBVT();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCDI TDRCDI = new TDRCDI();
    TDCOIMAE TDCOIMAE = new TDCOIMAE();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDRSTS TDRSTS = new TDRSTS();
    CICCUST CICCUST = new CICCUST();
    TDRCMST TDRCMST = new TDRCMST();
    TDCOMACE TDCOMACE = new TDCOMACE();
    CICQACRI CICQACRI = new CICQACRI();
    TDRINST TDRINST = new TDRINST();
    TDRMAGT TDRMAGT = new TDRMAGT();
    SCCGWA SCCGWA;
    TDCCHDC TDCCHDC;
    public void MP(SCCGWA SCCGWA, TDCCHDC TDCCHDC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCHDC = TDCCHDC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZCHDC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        if (pgmRtn) return;
        B110_CHANG_INF();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCHDC.OLD_AC);
        CEP.TRC(SCCGWA, TDCCHDC.NEW_AC);
        if (TDCCHDC.OLD_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_Z_AC_NO_INP_ERR);
        }
        if (TDCCHDC.NEW_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_Z_AC_NO_INP_ERR);
        }
    }
    public void B110_CHANG_INF() throws IOException,SQLException,Exception {
        WS_TIME = 0;
        TDRSMST.AC_NO = TDCCHDC.OLD_AC;
        TDRSMST.OPEN_DR_AC = TDCCHDC.OLD_AC;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_RENEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N') {
            WS_TIME += 1;
            if (TDRSMST.AC_NO.equalsIgnoreCase(TDCCHDC.OLD_AC)) {
                TDRSMST.AC_NO = TDCCHDC.NEW_AC;
            }
            if (TDRSMST.OPEN_DR_AC.equalsIgnoreCase(TDCCHDC.OLD_AC)) {
                TDRSMST.OPEN_DR_AC = TDCCHDC.NEW_AC;
            }
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
            T000_UPDATE_TDTSMST();
            if (pgmRtn) return;
            T000_RENEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRINST);
        TDRINST.STL_AC = TDCCHDC.OLD_AC;
        TDRINST.STL_INT_AC = TDCCHDC.OLD_AC;
        T000_STARTBR_TDTINST();
        if (pgmRtn) return;
        T000_READNEXT_TDTINST();
        if (pgmRtn) return;
        while (WS_INST_FLG != 'N') {
            if (TDCCHDC.OLD_AC.equalsIgnoreCase(TDRINST.STL_AC)) {
                TDRINST.STL_AC = TDCCHDC.NEW_AC;
            }
            if (TDCCHDC.OLD_AC.equalsIgnoreCase(TDRINST.STL_INT_AC)) {
                TDRINST.STL_INT_AC = TDCCHDC.NEW_AC;
            }
            CEP.TRC(SCCGWA, TDRINST.STL_AC);
            CEP.TRC(SCCGWA, TDRINST.STL_INT_AC);
            T000_UPDATE_TDTINST();
            if (pgmRtn) return;
            T000_READNEXT_TDTINST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTINST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.PAY_AC = TDCCHDC.OLD_AC;
        T000_STARTBR_TDTCDI();
        if (pgmRtn) return;
        T000_READNEXT_TDTCDI();
        if (pgmRtn) return;
        while (WS_CDI_FLG != 'N') {
            TDRCDI.PAY_AC = TDCCHDC.NEW_AC;
            CEP.TRC(SCCGWA, TDRCDI.PAY_AC);
            T000_UPDATE_TDTCDI();
            if (pgmRtn) return;
            T000_READNEXT_TDTCDI();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTCDI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRMAGT);
        TDRMAGT.KEY.AC_NO = TDCCHDC.OLD_AC;
        T000_STARTBR_TDTMAGT();
        if (pgmRtn) return;
        T000_RENEXT_TDTMAGT();
        if (pgmRtn) return;
        while (WS_MAGT_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRMAGT.KEY.AGT_NO);
            TDRMAGT.KEY.AC_NO = TDCCHDC.NEW_AC;
            T000_UPDATE_TDTMAGT();
            if (pgmRtn) return;
            T000_RENEXT_TDTMAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTMAGT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TIME);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "OR OPEN_DR_AC = :TDRSMST.OPEN_DR_AC";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_RENEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        TDTINST_RD.upd = true;
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READUP_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.upd = true;
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_UPDATE_TDTINST() throws IOException,SQLException,Exception {
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.REWRITE(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTCDI() throws IOException,SQLException,Exception {
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.REWRITE(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTMAGT() throws IOException,SQLException,Exception {
        TDRMAGT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRMAGT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRMAGT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        IBS.REWRITE(SCCGWA, TDRMAGT, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_BR.rp = new DBParm();
        TDTINST_BR.rp.TableName = "TDTINST";
        TDTINST_BR.rp.where = "STL_AC = :TDRINST.STL_AC "
            + "OR STL_INT_AC = :TDRINST.STL_INT_AC";
        TDTINST_BR.rp.upd = true;
        TDTINST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRINST, this, TDTINST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_INST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READNEXT_TDTINST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRINST, this, TDTINST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_INST_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_ENDBR_TDTINST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTINST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_BR.rp = new DBParm();
        TDTCDI_BR.rp.TableName = "TDTCDI";
        TDTCDI_BR.rp.where = "PAY_AC = :TDRCDI.PAY_AC";
        TDTCDI_BR.rp.upd = true;
        TDTCDI_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRCDI, this, TDTCDI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_CDI_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READNEXT_TDTCDI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCDI, this, TDTCDI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_CDI_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_ENDBR_TDTCDI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCDI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTMAGT() throws IOException,SQLException,Exception {
        TDTMAGT_BR.rp = new DBParm();
        TDTMAGT_BR.rp.TableName = "TDTMAGT";
        TDTMAGT_BR.rp.where = "AC_NO = :TDRMAGT.KEY.AC_NO";
        TDTMAGT_BR.rp.upd = true;
        TDTMAGT_BR.rp.order = "AGT_NO";
        IBS.STARTBR(SCCGWA, TDRMAGT, this, TDTMAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_MAGT_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_RENEXT_TDTMAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRMAGT, this, TDTMAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MAGT_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_MAGT_FLG = 'N';
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_ENDBR_TDTMAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTMAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

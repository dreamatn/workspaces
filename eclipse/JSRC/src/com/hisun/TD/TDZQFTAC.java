package com.hisun.TD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.GD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZQFTAC {
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTBVT_RD;
    DBParm TDTCMST_RD;
    brParm TDTSMST_BR = new brParm();
    brParm GDTSTAC_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMTP = "TD225";
    String K_OUTPUT_FMTC = "TD226";
    String K_OUTPUT_FMTG = "TD227";
    int K_OUTPUT_ROW = 5;
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String PGM_SCSSCLDT = "SCSSCLDT";
    TDZQFTAC_WS_OUT_DAT WS_OUT_DAT = new TDZQFTAC_WS_OUT_DAT();
    TDZQFTAC_WS_CIAC_D WS_CIAC_D = new TDZQFTAC_WS_CIAC_D();
    String WS_ERR_MSG = " ";
    char WS_SMST_FLG = ' ';
    int WS_I = 0;
    int WS_K = 0;
    int WS_T = 0;
    int WS_TT = 0;
    int WS_CNT = 0;
    char WS_STAC_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    CICQCIAC CICQCIAC = new CICQCIAC();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRMST DDRMST = new DDRMST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    CICACCU CICACCU = new CICACCU();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCCLDT SCCCLDT = new SCCCLDT();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    TDCQFTAC TDCQFTAC;
    public void MP(SCCGWA SCCGWA, TDCQFTAC TDCQFTAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCQFTAC = TDCQFTAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZQFTAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQCIAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCQFTAC.CI_NO);
        if (TDCQFTAC.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CINO_INPT);
        }
        if (TDCQFTAC.TRA_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_MUST_INPUT);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        while (CICQCIAC.DATA.LAST_FLG != 'Y') {
            CICQCIAC.DATA.CI_NO = TDCQFTAC.CI_NO;
            CICQCIAC.FUNC = '5';
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.DATA.ACR_AREA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CIAC_D);
            B020_GET_MSG();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_MSG() throws IOException,SQLException,Exception {
        WS_I = 1;
        WS_K = 0;
        while (WS_I < 100 
            && WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() != 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRSMST.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            if (TDRCMST.FRG_IND.trim().length() > 0 
                && !TDRCMST.FRG_IND.equalsIgnoreCase("OTH")) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = TDRCMST.OWNER_BRDP;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.TRA_TYP.equalsIgnoreCase(TDCQFTAC.TRA_TYP)) {
                    IBS.init(SCCGWA, TDRSMST);
                    TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
                    T000_STARTBR_TDTSMST();
                    if (pgmRtn) return;
                    T000_READNEXT_TDTSMST();
                    if (pgmRtn) return;
                    while (WS_SMST_FLG != 'N') {
                        WS_K += 1;
                        TDCQFTAC.OUTINF[WS_I-1].ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDCQFTAC.OUTINF[WS_I-1].CCY = TDRSMST.CCY;
                        TDCQFTAC.OUTINF[WS_I-1].CCY_TYPE = TDRSMST.CCY_TYP;
                        TDCQFTAC.OUTINF[WS_I-1].BAL = TDRSMST.BAL;
                        T000_READNEXT_TDTSMST();
                        if (pgmRtn) return;
                    }
                }
            }
            WS_I += 1;
        }
        TDCQFTAC.TOT_NUM = (short) WS_K;
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_READ_TDTCMST_NOR() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO "
            + "AND BV_TYP < > '1'";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void T000_READ_TDTCMST_YBT() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO "
            + "AND BV_TYP = '1'";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void T000_STARTBR_BY_STAC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "ST_AC = :GDRSTAC.ST_AC "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.order = "AC_SEQ";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLG = 'Y';
        } else {
            WS_STAC_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTSTAC_BR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

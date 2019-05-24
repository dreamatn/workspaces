package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9950 {
    brParm CITACR_BR = new brParm();
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    brParm CITACAC_BR = new brParm();
    DBParm CITACAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9950_AWA_9950 CIB9950_AWA_9950;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9950 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9950_AWA_9950>");
        CIB9950_AWA_9950 = (CIB9950_AWA_9950) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
            CITBAS_RD = new DBParm();
            CITBAS_RD.TableName = "CITBAS";
            IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "DELETE ACR INF");
                CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                CITACR_RD = new DBParm();
                CITACR_RD.TableName = "CITACR";
                IBS.DELETE(SCCGWA, CIRACR, CITACR_RD);
            }
            IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        }
        IBS.ENDBR(SCCGWA, CITACR_BR);
        IBS.init(SCCGWA, CIRACAC);
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRACR.KEY.AGR_NO = CIRACAC.AGR_NO;
            CITACR_RD = new DBParm();
            CITACR_RD.TableName = "CITACR";
            IBS.READ(SCCGWA, CIRACR, CITACR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "DELETE ACAC INF");
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                CITACAC_RD = new DBParm();
                CITACAC_RD.TableName = "CITACAC";
                IBS.DELETE(SCCGWA, CIRACAC, CITACAC_RD);
            }
            IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        }
        IBS.ENDBR(SCCGWA, CITACAC_BR);
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
